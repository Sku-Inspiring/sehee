package org.zerock.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@AllArgsConstructor
@Getter
public class ReplyPageDTO {
    //전체 댓글의 수
    private int replyCnt;
    // 댓글 목록
    private List<ReplyVO> list;
}
