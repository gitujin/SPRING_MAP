 const clickLikeUrl = "/image/heart.png";
 const emptyLikeUrl = "/image/empty_heart.png";

    /** 좋아요 유무에 따라 하트 그림 다르게 보여줌 **/
    //브라우저가 웹 문서를 읽기 시작하고 DOM이 생성되면 실행되는 메소드
    $(function(){

        // 현재 로그인한 유저가 해당 게시물을 좋아요 했다면 likeVal = true,
  		// 좋아요하지 않았다면 false
        let likeVal = $('#like_check').val(); // 데이터가 있으면 true
        const likeImg = $('#likeImg');

        console.log("likeVal : " + likeVal);

        if(likeVal === 'true'){
            // 데이터가 존재하면 화면에 채워진 하트 보여줌
            $('#likeImg').attr("src", clickLikeUrl);
        } else if(likeVal === 'false'){
            // 데이터가 없으면 화면에 빈 하트 보여줌
            $('#likeImg').attr("src", emptyLikeUrl);
        }
    });

    /** 좋아요 클릭 시 실행 **/
    $('#likeImg').click(function() {

        const postId = $('#boardId').val();
        console.log(postId);
        const likeVal = $('#like_check').val();

        console.log(likeVal);
        if (likeVal === 'true') {
            const con_check = confirm("현재 게시물 추천을 취소하시겠습니까?")
            if (con_check) {
                console.log("추천 취소 진입");
                $.ajax({
                    type: 'POST',
                    url: '/rest/community/post/like/' + postId,
                    contentType: 'application/json; charset=utf-8'
                }).done(function () {
                    $('#likeImg').attr("src", emptyLikeUrl);
                    location.reload();
                }).fail(function (error) {
                    alert(JSON.stringify(error));
                })
            }
        } else if(likeVal === 'false'){
            const con_check = confirm("현재 게시물을 추천하시겠습니까?");
            if (con_check) {
                console.log("추천 진입");
                $.ajax({
                    type: 'POST',
                    url: '/rest/community/post/like/' + postId,
                    contentType: 'application/json; charset=utf-8'
                }).done(function () {
                    $('#likeImg').attr("src", clickLikeUrl);
                    location.reload();
                }).fail(function (error) {
                    alert(JSON.stringify(error));
                })
            }
        }
    });
