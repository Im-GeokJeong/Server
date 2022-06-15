package IGJ.imgeokjeong.post.controller;

import IGJ.imgeokjeong.errors.response.CommonResponse;
import IGJ.imgeokjeong.post.dto.AuthorizeRequest;
import IGJ.imgeokjeong.post.dto.CreateRequest;
import IGJ.imgeokjeong.post.dto.UpdateRequest;
import IGJ.imgeokjeong.post.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/api/post")
@RestController
public class PostController {

    private final PostService postService;

    @Operation(summary = "add post", description = "개인 임대 게시글 생성")
    @PostMapping()
    public CommonResponse postAdd(@RequestBody @Valid CreateRequest request) {
        return postService.create(request);
    }

    @Operation(summary = "get post", description = "게시글 상세보기")
    @GetMapping("/{postId}")
    public CommonResponse postDetail(@PathVariable Long postId) {
        return postService.detail(postId);
    }

    @Operation(summary = "get post list", description = "전체 게시글 리스트")
    @GetMapping("/all")
    public CommonResponse postList() {
        return postService.list();
    }

    @Operation(summary = "get post list by title", description = "제목으로 게시글 리스트")
    @GetMapping("/title/{title}")
    public CommonResponse postTitleList(@PathVariable String title) {
        return postService.titleList(title);
    }

    @Operation(summary = "get post list by region", description = "지역으로 게시글 리스트")
    @GetMapping("/region/{region}")
    public CommonResponse postRegionList(@PathVariable String region) {
        return postService.regionList(region);
    }

    @Operation(summary = "update post", description = "게시글 내용 수정")
    @PutMapping()
    public CommonResponse postModify(@RequestBody @Valid UpdateRequest request) {
        return postService.update(request);
    }

    @Operation(summary = "delete post", description = "게시글 삭제")
    @DeleteMapping("/{postId}")
    public CommonResponse postRemove(@PathVariable Long postId) {
        return postService.delete(postId);
    }

    @Operation(summary = "PIN number authentication", description = "수정 & 삭제를 위한 4자리 핀번호 인증")
    @GetMapping("/auth")
    public CommonResponse authorize(@RequestBody @Valid AuthorizeRequest request) {
        return postService.authorize(request);
    }
}
