$("#comment-btn").click(function (){
    console.log('댓글 등록 버튼 클릭');

    var commentBean = {
        writer : $("#userId").val(),
        content : $("#comment-input").val(),
        postId : $("#postId").val(),
    }

    $.ajax({
        type: 'post',
        url : '/board/comment',
        data : commentBean,
    })
    .done(function (fragment) {
            $('#comment-list').replaceWith(fragment);
    });

    })