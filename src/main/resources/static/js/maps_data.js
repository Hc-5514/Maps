var map;
var temp;
var wind;
var humidity;
var rain;

function initMap() {
    const map = new google.maps.Map(document.getElementById('map'), {
        zoom: 15,
        center: {lat: 35.89042512426673, lng: 128.61219690022736},
    });

    const spot = [
        {label: "A", name: "동구 신암동", lat: 35.883926, lng: 128.624642, temp: A_temp, wind: A_wind, humidity: A_humidity, rain: A_rain},
        {label: "B", name: "동구 효목동", lat: 35.880411, lng: 128.642768, temp: B_temp, wind: B_wind, humidity: B_humidity, rain: B_rain},
        {label: "C", name: "북구 서변동", lat: 35.925667, lng: 128.594018, temp: C_temp, wind: C_wind, humidity: C_humidity, rain: C_rain},
        {label: "D", name: "서구 중리동", lat: 35.863822, lng: 128.540486, temp: D_temp, wind: D_wind, humidity: D_humidity, rain: D_rain},
    ];

    const bounds = new google.maps.LatLngBounds();

    spot.forEach(({ label, name, lat, lng , temp, wind, humidity, rain}) => {
        const marker = new google.maps.Marker({
            position: { lat, lng },
            label,
            map: map,
        });
        bounds.extend(marker.position);
        var content = "온도: " + temp + "<br>" + "풍속: " + wind + "<br>" + "습도: " + humidity + "<br>" + "강수: " + rain;
        var infowindow = new google.maps.InfoWindow({ content: content});
        google.maps.event.addListener(marker, "click", function() {
            infowindow.open(map, marker);
        });
    });
    map.fitBounds(bounds);
}

// selectBox 기본값 설정
$('#select1').val(select1).prop("selected", true);
$('#select2').val(select2).prop("selected", true);
$('#select3').val(select3).prop("selected", true);
