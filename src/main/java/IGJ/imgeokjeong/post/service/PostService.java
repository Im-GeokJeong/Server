package IGJ.imgeokjeong.post.service;

import IGJ.imgeokjeong.errors.exception.NotExistPostException;
import IGJ.imgeokjeong.errors.response.CommonResponse;
import IGJ.imgeokjeong.errors.response.ResponseService;
import IGJ.imgeokjeong.post.domain.Post;
import IGJ.imgeokjeong.post.dto.AuthorizeRequest;
import IGJ.imgeokjeong.post.dto.CreateRequest;
import IGJ.imgeokjeong.post.dto.UpdateRequest;
import IGJ.imgeokjeong.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final ResponseService responseService;

    @Transactional
    public CommonResponse create(CreateRequest request) {
        postRepository.save(new Post(request));
        return responseService.getSuccessResponse();
    }

    @Transactional
    public CommonResponse detail(Long id) {
        return responseService.getSingleResponse(findById(id));
    }

    @Transactional
    public CommonResponse list() {
        return responseService.getListResponse(postRepository.findAll());
    }

    @Transactional
    public CommonResponse titleList(String title) {
        return responseService.getListResponse(postRepository.findByTitleContains(title));
    }

    @Transactional
    public CommonResponse regionList(String region) {
        return responseService.getListResponse(postRepository.findByRegion(region));
    }

    @Transactional
    public CommonResponse update(UpdateRequest request) {
        findById(request.getId()).update(request);
        return responseService.getSuccessResponse();
    }

    @Transactional
    public CommonResponse delete(Long id) {
        postRepository.delete(findById(id));
        return responseService.getSuccessResponse();
    }

    @Transactional
    public CommonResponse authorize(AuthorizeRequest request) {
        if(!findById(request.getId())
                .getPin().equals(request.getPin()))
            return responseService.getErrorResponse(HttpStatus.BAD_REQUEST.value(), "잘못된 핀번호 입니다");
        return responseService.getSuccessResponse();
    }

    //
    // private
    //

    private Post findById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(NotExistPostException::new);
    }
}
