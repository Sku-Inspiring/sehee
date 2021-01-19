<%--
  Created by IntelliJ IDEA.
  User: sehee
  Date: 2020-11-06
  Time: 오후 9:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<%@include file="../includes/header.jsp" %>
<head>
    <body>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Sehee Tables</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Board List Page
                        <button id="regBtn" type="button" class="btn btn-xs pull-right">Register New Board</button>
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                            <thead>
                            <tr>
                                <th>#번호</th>
                                <th>제목</th>
                                <th>작성자</th>
                                <th>작정일</th>
                                <th>수정일</th>
                            </tr>
                            </thead>
                            <c:forEach items="${list}" var="board">
                                <tr>
                                    <td><a href='/board/get?bno=<c:out value="${board.bno}"/>'> <c:out value="${board.bno}"/></a></td>
                                    <td><c:out value="${board.title}"/></td>
                                    <td><c:out value="${board.writer}"/></td>
                                    <td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.regdate}"/></td>
                                    <td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.updatedate}"/></td>
                                </tr>
                            </c:forEach>
                        </table>

                        <div class="pull-right">
                            <ul class="pagination">
                                <c:if test="${pageMaker.prev}">
                                <li class="paginate_button previous"><a href="#">Previous</a> </li>
                                </c:if>

                                <c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
                                    <li class="paginate_button ${pageMaker.cri.pageNum == num ? "active":""}"><a href="${num}">${num}</a> </li>
                                </c:forEach>

                                <c:if test="${pageMaker.next}">
                                    <li class="paginate_button next"><a href="${pageMaker.endPage +1 }">Next</a> </li>
                                </c:if>

                            </ul>
                        </div>
                    </div>
                    <form id='actionForm' action="/board/list" method='get'>
                        <input type='hidden' name='pageNum' value='${pageMaker.cri.pageNum}'>
                        <input type='hidden' name='amount' value='${pageMaker.cri.amount}'>
                    </form>

                        <!-- Modal  추가 -->
                        <div class="modal fade" id="myModal" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal"
                                                aria-hidden="true">&times;</button>
                                        <h4 class="modal-title" id="myModalLabel">Modal title</h4>
                                    </div>
                                    <div class="modal-body">처리가 완료되었습니다.</div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default"
                                                data-dismiss="modal">Close</button>
                                        <button type="button" class="btn btn-primary">Save
                                            changes</button>
                                    </div>
                                </div>
                                <!-- /.modal-content -->
                            </div>
                            <!-- /.modal-dialog -->
                        </div>
                        <!-- /.modal -->
                    </div>
                </div>
    </div>
    <!-- /#page-wrapper -->
</div>
<!-- /#wrapper -->
        <%@include file="../includes/footer.jsp" %>
</body>
</html>

<script type="text/javascript">
    $(document).ready(function () {
        var result = '<c:out value="${result}"/>';
        checkModal(result);
        history.replaceState({},null,null);
        function checkModal(result) {
            if(result === '' || history.state ){
                return;
            }
            if (parseInt(result) > 0){
                $(".modal-body").html("게시글 " + parseInt(result) + "번이 등록되었습니다.");
            }
            $("#myModal").modal("show");
        }
        $("#regBtn").on("click", function () {
            self.location = "/board/register";
        });

        var actionForm = $("#actionForm");

        $(".paginate_button a").on("click", function (e) {
            e.preventDefault();
            console.log('click');
            actionForm.find("input[name='pageNum']").val($(this).attr("href"));
            actionForm.submit();
        });
    });
</script>