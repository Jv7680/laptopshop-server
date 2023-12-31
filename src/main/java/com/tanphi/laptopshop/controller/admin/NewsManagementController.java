package com.tanphi.laptopshop.controller.admin;

import com.tanphi.laptopshop.controller.ApiResponse;
import com.tanphi.laptopshop.entity.News;
import com.tanphi.laptopshop.exception.BadRequestException;
import com.tanphi.laptopshop.mapper.NewsMapper;
import com.tanphi.laptopshop.request.news.NewsRequest;
import com.tanphi.laptopshop.service.MyTelegramBot;
import com.tanphi.laptopshop.service.NewsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

@PreAuthorize("hasAuthority('ADMIN')")
@RestController
@RequestMapping("admin/news")
public class NewsManagementController {
	@Autowired
	private NewsService newsService;
	
	@Autowired
	private MyTelegramBot telegramBot;


	@GetMapping("/{id}")
	public ResponseEntity<?> GetNewsById(@PathVariable int id) {
		News news = newsService.GetNewsById(id);
		if (news == null) {
			throw new BadRequestException("Không có tin tức có id: " + String.valueOf(id));
		}
		return ResponseEntity.ok(news);
	}

	@GetMapping("/search")
	public ResponseEntity<Object> GetListNewsPage(
			@RequestParam(value = "page", required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size,
			@RequestParam(value = "keyword", required = false) String keyword) {
		int pageSize = 10;
		if (size.isPresent()) {
			pageSize = size.get();
		}
		int pageNumber = 1;
		if (page.isPresent()) {
			pageNumber = page.get();
			page = Optional.of(pageNumber - 1);

		} else {
			page = Optional.of(0);
		}
		Pageable pageable = PageRequest.of(page.get(), pageSize);
		Page<News> listAllNewsPage = null;
		Page<News> listNewsByKeywordPage = null;
		if (keyword.isEmpty() || keyword == null) {
			listAllNewsPage = newsService.GetListNewsPage(pageable);
			int totalPages = listAllNewsPage.getTotalPages();
			int currentPage = listAllNewsPage.getNumber() + 1;
			List<News> listNews = listAllNewsPage.getContent();
			if (listNews == null || listNews.size() == 0) {
				throw new BadRequestException("Không tìm được tin tức");
			}
			return ResponseEntity.status(HttpStatus.OK)
					.body(NewsMapper.toResponeGetListNewsPage(listNews, currentPage, totalPages));
		} else {
			// String keywordSearch="%"+keyword+"%";
			listNewsByKeywordPage = newsService.GetListNewsPageByKeyword(pageable, keyword);
			int totalPages = listNewsByKeywordPage.getTotalPages();
			int currentPage = listNewsByKeywordPage.getNumber() + 1;
			List<News> listNews = listNewsByKeywordPage.getContent();
			if (listNews == null || listNews.size() == 0) {
				throw new BadRequestException("Không tìm được tin tức");
			}
			return ResponseEntity.status(HttpStatus.OK)
					.body(NewsMapper.toResponeGetListNewsPage(listNews, currentPage, totalPages));
		}
	}
	
	@PostMapping("")
	public ResponseEntity<?> AddNews(@Valid @RequestBody NewsRequest request,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.NOT_ACCEPTABLE);
		}
		newsService.AddNews(request);
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("Thêm tin tức thành công");
	    telegramBot.sendTelegramMessage("Tin mới nhất  : " + request.getTitle() + " truy cập ngay để cập nhật http://localhost:4004/news");

		return ResponseEntity.ok(apiResponse);
	}

	@PutMapping("/update")
	public ResponseEntity<?> UpdateNews(@Valid @RequestBody NewsRequest request,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.NOT_ACCEPTABLE);
		}
		newsService.UpdateNews(request);
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("Cập nhật tin tức thành công");
		return ResponseEntity.ok(apiResponse);
	}

	@PutMapping("/delete/{newsId}")
	public ResponseEntity<?> DeleteNews(@PathVariable Integer newsId) {
		newsService.DeleteNews(newsId);
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("Xóa tin tức thành công");
		return ResponseEntity.ok(apiResponse);
	}
}
