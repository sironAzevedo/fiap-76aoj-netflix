package com.netflix.series.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.series.model.dto.SerieDTO;
import com.netflix.series.model.dto.SerieLikeDTO;
import com.netflix.series.model.dto.SerieUserDTO;
import com.netflix.series.model.dto.SerieWatchedDTO;
import com.netflix.series.model.dto.TopSerieCategoryResponseDTO;
import com.netflix.series.service.ISerieService;

@RestController
@RequestMapping(value = "/series")
public class SerieController {

	@Autowired
	private ISerieService service;

	@ResponseBody
	@GetMapping("/detail")
	@ResponseStatus(value = HttpStatus.OK)
	public SerieDTO detail(@RequestParam(value = "serie") Long serie) {
		return service.detail(serie);
	}

	@ResponseBody
	@GetMapping("/by-category")
	@ResponseStatus(value = HttpStatus.OK)
	public Page<SerieDTO> byCategory(@RequestParam(value = "category") Long category, Pageable pageable) {
		return service.byCategory(Long.valueOf(category), pageable);
	}

	@ResponseBody
	@GetMapping("/keyword")
	@ResponseStatus(value = HttpStatus.OK)
	public Page<SerieDTO> getKeyword(@RequestParam(value = "word") String word, Pageable pageable) {
		return service.getKeyword(word, pageable);
	}

	@ResponseBody
	@PostMapping("/watched")
	@ResponseStatus(value = HttpStatus.OK)
	public void serieWatched(@Valid @RequestBody SerieWatchedDTO dto) {
		service.watched(dto);
	}

	@ResponseBody
	@GetMapping("/watched")
	@ResponseStatus(value = HttpStatus.OK)
	public Page<SerieWatchedDTO> serieWatched(@RequestParam(value = "user") String user, Pageable pageable) {
		return service.watched(user, pageable);
	}
	
	@ResponseBody
	@GetMapping("/likes")
	@ResponseStatus(value = HttpStatus.OK)
    public Page<SerieLikeDTO> likes(@RequestParam(value = "user") String user, Pageable pageable) {
        return service.likes(user, pageable);
    }
	
	@ResponseBody
	@PostMapping("/future")
	@ResponseStatus(value = HttpStatus.OK)
	public void future(@Valid @RequestBody SerieUserDTO dto) {
		service.watchFuture(dto.getUser(), dto.getSerie().getId());
	}
	
	@ResponseBody
	@GetMapping("/futures")
	@ResponseStatus(value = HttpStatus.OK)
    public Page<SerieUserDTO> futures(@RequestParam(value = "user") String user, Pageable pageable) {
        return service.watchFuture(user, pageable);
    }
	
	@ResponseBody
	@GetMapping("/top/by-category")
	@ResponseStatus(value = HttpStatus.OK)
    public List<TopSerieCategoryResponseDTO> getTopSerieByCategory(Pageable pageable) {
        return service.getTopSerieByCategory(pageable);
    }
}
