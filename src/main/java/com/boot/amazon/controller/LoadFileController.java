package com.boot.amazon.controller;

import com.boot.amazon.dto.CsvRecordDto;
import com.boot.amazon.mapper.ProductMapper;
import com.boot.amazon.mapper.ReviewMapper;
import com.boot.amazon.mapper.UserMapper;
import com.boot.amazon.model.Product;
import com.boot.amazon.model.Review;
import com.boot.amazon.model.User;
import com.boot.amazon.service.FileService;
import com.boot.amazon.service.ParseService;
import com.boot.amazon.service.ProductSevice;
import com.boot.amazon.service.ReviewService;
import com.boot.amazon.service.RoleService;
import com.boot.amazon.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@RestController
public class LoadFileController {
    private static final String LOAD_FROM_URL =
            "https://spring-boot-aws-revievers.s3.eu-central-1.amazonaws.com/Reviews.csv";
    private static final String SAVE_TO_PATH = "file.csv";
    private final FileService fileService;
    private final ParseService<CsvRecordDto> parseService;
    private final ProductMapper productMapper;
    private final ReviewMapper reviewMapper;
    private final UserMapper userMapper;
    private final ProductSevice productSevice;
    private final UserService userService;
    private final ReviewService reviewService;
    private final RoleService roleService;

    public LoadFileController(FileService fileService,
                              ParseService<CsvRecordDto> parseService,
                              ProductMapper productMapper, ReviewMapper reviewMapper,
                              UserMapper userMapper, ProductSevice productSevice,
                              UserService userService, ReviewService reviewService,
                              RoleService roleService) {
        this.fileService = fileService;
        this.parseService = parseService;
        this.productMapper = productMapper;
        this.reviewMapper = reviewMapper;
        this.userMapper = userMapper;
        this.productSevice = productSevice;
        this.userService = userService;
        this.reviewService = reviewService;
        this.roleService = roleService;
    }

    @GetMapping("/load")
    public void loadAndSaveFile() throws IOException {
        fileService.saveFileFromUrl(LOAD_FROM_URL, SAVE_TO_PATH);
        List<CsvRecordDto> parse = parseService.parse(new File(SAVE_TO_PATH));

        for (CsvRecordDto recordDto : parse) {
            Product product = productMapper.map(recordDto);
            productSevice.save(product);
            User user = userMapper.map(recordDto);
            user.setRoles(Set.of(roleService.getByRoleName("USER").get()));
            userService.save(user);
            Review review = reviewMapper.map(recordDto);
            reviewService.save(review);
        }
    }
}
