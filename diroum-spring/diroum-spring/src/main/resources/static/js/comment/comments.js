    /** 댓글 작성 **/
    $("#comment-btn").click(function () {

        const data = {
            content : $("#comment-input").val(),
            postId : $("#postId").val()
        }

        if(!data.content || data.content === ""){
            alert('공백 또는 입력하지 않은 부분이 존재합니다.');
            return false;
        } else{
            const con_check = confirm("댓글을 작성하시겠습니까?");
            if(con_check){
                $.ajax({
                    type: 'post',
                    url: '/board/' + data.postId + "/comment",
                    data: JSON.stringify(data),
                    contentType: 'application/json; charset=utf-8',
                    beforeSend: function (xhr) {
                        xhr.setRequestHeader(header, token);
                    }
                }).done(function(){
                    location.reload();
                }).fail(function(error){
                    alert(JSON.stringify(error));
                })
            }
        }
    });

    /** 댓글 삭제 **/
    function commentDelete(commentId){

        const con_check = confirm("댓글을 삭제하시겠습니까?");

        if(con_check === true){
            $.ajax({
                type: 'delete',
                url: '/board/'+postId+'/comment/'+'/delete',
            }).done(function(){
                alert('댓글이 삭제되었습니다.');
                window.location.reload();
            }).fail(function(error){
                alert(JSON.stringify(error));
            });
        }
    }