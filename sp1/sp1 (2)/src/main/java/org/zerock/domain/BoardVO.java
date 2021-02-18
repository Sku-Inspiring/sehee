package org.zerock.domain;

import lombok.Data;

import java.util.Date;

@Data
public class BoardVO {
    private Long bno;
    private String title;
    private  String content;
    private String writer;
    private Date regdate;
    private Date updatedate;

    //게시물 목록에 댓글의 수 추가하는 변수
    private int replycnt;
}
