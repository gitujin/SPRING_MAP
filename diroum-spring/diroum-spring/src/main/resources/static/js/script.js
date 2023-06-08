/*
1. 지도 생성 & 확대 축소 컨트롤러
*/

var container = document.getElementById('map');
var options = {
center: new kakao.maps.LatLng(35.9479447,126.9575551),
level: 9
};

//지도 생성
var map = new kakao.maps.Map(container, options);

// 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
var mapTypeControl = new kakao.maps.MapTypeControl();

// 지도에 컨트롤을 추가해야 지도위에 표시됩니다
// kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
var zoomControl = new kakao.maps.ZoomControl();
map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);


/*
2. 더미데이터 준비하기 (제목, 주소, 카테고리)
*/

async function getDataSet(categoryId){
    let qs = categoryId;
    if(!qs) {
        qs="";
    }

    var dataSet;

    $.ajax({
        url : "http://localhost:8080/Dairoum?category=" + qs,
        type : "get",
        async : false,
        dataType : "json",
        success : function (data){
            console.log("1", data);
            dataSet = data;
        }
    })

    console.log("2", dataSet);

    return dataSet;
}

getDataSet();

/*
3. 여러개 마커 찍기
*/
// 주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();

// 주소-좌표 변환 함수
function getCoordsByAddress(address){

    return new Promise((resolve, reject) => {
        // 주소로 좌표를 검색합니다
        geocoder.addressSearch(address,function(result,status){
            //정상적으로 검색이 완료됐으면
            if(status === kakao.maps.services.Status.OK) {
                var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
                resolve(coords);
                return;
            }
            reject(new Error("getCoordsByAddress Error: not Vaild Address"))
        });
    });
}

/*
4. 마커에 인포윈도우 붙이기
*/

async function setMap(dataSet){

    let a=0;

    for (let value of dataSet) {

    var imageSrc = `https://i.ibb.co/X73nkP6/map-marker-1.png`, // 마커이미지의 주소입니다
    imageSize = new kakao.maps.Size(40, 40); // 마커이미지의 크기입니다
    //imageOption = {offset: new kakao.maps.Point(27, 69)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

    // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize),
    markerPosition = new kakao.maps.LatLng(37.54699, 127.09598); // 마커가 표시될 위치입니다

        // 마커를 생성합니다
        let coords = await getCoordsByAddress(value.address);

        let marker = new kakao.maps.Marker({
                map: map, // 마커를 표시할 지도
                position: coords, // 마커를 표시할 위치
                image:markerImage
                });

        markerArray.push(marker);
        markers.push(marker);

        //console.log("3", value.address);

    // 커스텀 오버레이를 생성합니다
    let customOverlay = new kakao.maps.CustomOverlay({
        position: coords,
        clickable: true,
        xAnchor: 0.5,
        yAnchor: 1.3
    });

    // 커스텀 오버레이 엘리먼트를 만들고, 컨텐츠를 추가합니다
    let contentsHead = document.createElement("div");
    contentsHead.className = "head";

    let contentsTitle = document.createElement("a");
    contentsTitle.className = "title";
    contentsTitle.innerHTML = value.title;

    let contentsDesc = document.createElement("div");
    contentsDesc.className = "desc";

    let contentsAddress = document.createElement("span");
    contentsAddress.className = "address";
    contentsAddress.innerHTML = value.address;

    contentsHead.append(contentsTitle, contentsDesc);
    contentsDesc.append(contentsAddress);

    customOverlay.setContent(contentsHead);

    customOverlayArray.push(customOverlay);

    kakao.maps.event.addListener(marker, "click", () => {
        if (this.clickedOveray) {
          this.clickedOveray.setMap(null);
        }
        customOverlay.setMap(map);
        this.clickedOveray = customOverlay;
        // 클릭한 곳으로 중심 옮기기
        map.panTo(coords);
      });

    kakao.maps.event.addListener(map, "click", function () {
        customOverlay.setMap(null);
      });
    }
    console.log("markerArray: ",markerArray);

    console.log("markers: ", markers);
    clusterer.addMarkers(markers);


}

/**
5. 카테고리 분류
*/

// 카테고리
const categoryMap = {
    allGasStation : "전체 주유소",
    payBackGas : "페이백 주유소",
    noPayBack : "페이백 제외 지점",
};

const categoryList = document.querySelector(".category-list");
categoryList.addEventListener("click", categoryHandler);

async function categoryHandler(event){ //카테고리 클릭했을 때

    const categoryId = event.target.id;
        console.log("3", categoryId);

    const category = categoryMap[categoryId];
        console.log("4", category);


    try {
    // 데이터 분류
    let categorizedDataSet = await getDataSet(categoryId);

    // 기존 마커 삭제
    closeMarker();

    // 인포윈도우 삭제
    closeCustomArr();

    // 기존 클러스터러 삭제
    closeClusterer();

    setMap(categorizedDataSet);

    } catch (error) {
        console.error(error);
    }
}

let markerArray = [];
function closeMarker(){
    for(marker of markerArray){
        marker.setMap(null);
    }
}

let customOverlayArray = [];
function closeCustomArr(){
    for(customOverlay of customOverlayArray)
    customOverlay.setMap(null);
}

let markers = [];
function closeClusterer(){
    markers.length=0;
    clusterer.clear();

}

/**
  6. 마커 클러스터러 사용하기
  */
    let clusterer = new kakao.maps.MarkerClusterer({
         map: map,
         averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
         minLevel: 6 // 클러스터 할 최소 지도 레벨
        });
