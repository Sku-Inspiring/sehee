<%--
  Created by IntelliJ IDEA.
  User: hong
  Date: 2021-01-19
  Time: 오후 2:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@include file="../includes/header.jsp" %>

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Board Read</h1>
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                Board Read Page
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <div class="form-group">
                        <label>Bno</label> <input class="form-control" name='bno'
                                                  value='<c:out value="${board.bno }"/>' readonly="readonly">
                    </div>
                <div class="form-group">
                    <label>Title</label> <input class="form-control" name='title'
                                              value='<c:out value="${board.title }"/>' readonly="readonly">
                </div>
                <div class="form-group">
                <label>Text area</label> <textarea class="form-control" rows="3" name='content'
                                                   readonly="readonly"> <c:out value="${board.content}"/></textarea></div>
                <div class="form-group">
                <label>Writer</label> <input class="form-control" name='writer'
                                          value='<c:out value="${board.writer }"/>' readonly="readonly">
            </div>
                    <button data-oper='modify' class="btn btn-default" onclick="location.href=
                            '/board/modify?bno=<c:out value="${board.bno}"/>'">Modify</button>
                    <button data-oper='list' class="btn btn-info" onclick="location.href='/board/list'">List</button>
                <form id='operForm' action="/board/modify" method="get">
                    <input type='hidden' id='bno' name='bno' value='<c:out value="${board.bno}"/>'>
                    <input type='hidden' name='pageNum' value='<c:out value="${cri.pageNum}"/>'>
                    <input type='hidden' name='amount' value='<c:out value="${cri.amount}"/>'>
                </form>
            </div>
            <!-- end panel-body -->
        </div>
        <!-- end panel -->
    </div>
</div>
<div class='row'>

    <div class="col-lg-12">

        <!-- /.panel -->
        <div class="panel panel-default">
            <%--       <div class="panel-heading">--%>
            <%--        <i class="fa fa-comments fa-fw"></i> Reply--%>
            <%--      </div> --%>

            <div class="panel-heading">
                <i class="fa fa-comments fa-fw"></i> Reply
                <button id='addReplyBtn' class='btn btn-primary btn-xs pull-right'>New Reply</button>
            </div>
                <div class="panel-body">
                    <ul class="chat">
                        <li class="left clearfix" data-rno="12">

                        </li>
                    </ul>
                </div>
            <div class="panel-footer"></div>
        </div>
    </div>
</div>
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">REPLY MODAL</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label>Reply</label>
                    <input class="form-control" name='reply' value='New Reply!!!!'>
                </div>
                <div class="form-group">
                    <label>Replyer</label>
                    <input class="form-control" name='replyer' value='replyer'>
                </div>
                <div class="form-group">
                    <label>Reply Date</label>
                    <input class="form-control" name='replyDate' value='2018-01-01 13:13'>
                </div>

            </div>
            <div class="modal-footer">
                <button id='modalModBtn' type="button" class="btn btn-warning">Modify</button>
                <button id='modalRemoveBtn' type="button" class="btn btn-danger">Remove</button>
                <button id='modalRegisterBtn' type="button" class="btn btn-primary">Register</button>
                <button id='modalCloseBtn' type="button" class="btn btn-default">Close</button>
            </div>          </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->


<script type="text/javascript" src="/resources/js/reply.js"></script>


<script>

    $(document).ready(function () {

        var bnoValue = '<c:out value="${board.bno}"/>';
        // var replyUL = $(".chat");

        showList(1);

        function showList(page){
            console.log("show list:" + page);
            replyService.getList({bno:bnoValue,page: page|| 1 }, function(replyCnt, list) {

                if(page == -1){
                    pageNum = Math.ceil(replyCnt/10.0);
                    showList(pageNum);
                    return;
                }

                var str="";

                if(list == null || list.length == 0){
                    return;
                }
                for (var i = 0, len = list.length || 0; i < len; i++) {
                    str +="<li class='left clearfix' data-rno='"+list[i].rno+"'>";
                    str +="  <div><div class='header'><strong class='primary-font'>["
                        +list[i].rno+"] "+list[i].replyer+"</strong>";
                    str +="    <small class='pull-right text-muted'>"
                        +replyService.displayTime(list[i].replyDate)+"</small></div>";
                    str +="    <p>"+list[i].reply+"</p></div></li>";
                }
                $(".chat").html(str);
            });
        }
        var modal = $(".modal");
        var modalInputReply = modal.find("input[name='reply']");
        var modalInputReplyer = modal.find("input[name='replyer']");
        var modalInputReplyDate = modal.find("input[name='replyDate']");

        var modalModBtn = $("#modalModBtn");
        var modalRemoveBtn = $("#modalRemoveBtn");
        var modalRegisterBtn = $("#modalRegisterBtn");

        // New Replyer 버튼 클릭 시 모달창 띄우기
        $("#addReplyBtn").on("click", function (e) {
            modal.find("input").val("");
            modalInputReplyDate.closest("div").hide();
            modal.find("button[id != 'modalCloseBtn']").hide();

            modalRegisterBtn.show();

            $(".modal").modal("show");
        });

        // 새로운 댓글 추가
        modalRegisterBtn.on("click", function (e) {
            var reply = {
                reply: modalInputReply.val(),
                replyer: modalInputReplyer.val(),
                bno: bnoValue
            };
        replyService.add(reply, function (result) {
            alert(result);
            modal.find("input").val("");
            modal.modal("hide");

            // reply list에 추가한거 보이게 하기
            showList(-1);
        });
        });

        //댓글 조회 클릭 이벤트 처리
        $(".chat").on("click", "li", function (e){
            var rno = $(this).data("rno");
            replyService.get(rno, function (reply){
                modalInputReply.val(reply.reply);
                modalInputReplyer.val(reply.replyer);
                modalInputReplyDate.val(replyService.displayTime(reply.replyDate)).attr("readonly", "readonly");
                modal.data("rno", reply.rno);

                modal.find("button[id != 'modalCloseBtn']").hide();
                modalModBtn.show();
                modalRemoveBtn.show();

                $(".modal").modal("show");
            });
        });
        // 댓글 수정
        modalModBtn.on("click", function (e) {
            var reply = {rno: modal.data("rno"), reply: modalInputReply.val()};
            replyService.update(reply, function (result) {
                alert(result);
                modal.modal("hide");
                showList(1);
            });
        });

        // 댓글 삭제
        modalRemoveBtn.on("click", function (e) {
            var rno = modal.data("rno");
            replyService.remove(rno, function (result) {
                alert(result);
                modal.modal("hide");
                showList(1);
            });
        });
    });
</script>
<script type="text/javascript">
    $(document).ready(function(){
        var operForm = $("#operForm");
        $("button[data-oper='modify']").on("click", function (e){
            operForm.attr("action","/board/modify").submit();
        });

        $("button[data-oper='list']").on("click", function (e){
            operForm.find("#bno").remove();
            operForm.attr("action","/board/list")
            operForm.submit();
        });
    });
</script>

<script>
    console.log("===========");
    console.log("JS TEST");

    var bnoValue = '<c:out value="${board.bno}"/>';

    replyService.add({
        reply:"JS TEST", replyer:"tester", bno:bnoValue
    }, function (result) {
        alert("RESULT"+result);
    });
</script>
<%--<script>--%>
<%--    console.log("===========");--%>
<%--    console.log("JS TEST");--%>

<%--    var bnoValue = '<c:out value="${board.bno}"/>';--%>

<%--    replyService.getList({bno:bnoValue, page:1}, function (list) {--%>
<%--        for (var i = 0, len = list.length||0; i<len; i++){--%>
<%--            console.log(list[i]);--%>
<%--        }--%>
<%--    });--%>
<%--</script>--%>
<script>
        console.log("===========");
        console.log("JS TEST");
    replyService.get(10, function (data) {
        console.log(data);
    })
</script>
<%@include file="../includes/footer.jsp" %>
