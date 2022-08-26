function initMap () {
    const map = new google.maps.Map(document.getElementById("map2"), {
        zoom: 15,
        center: {lat: 35.89042512426673, lng: 128.61219690022736},
    });

    const spot = [
        {label: "A", name: "동구 신암동", lat: 35.883926, lng: 128.624642},
        {label: "B", name: "동구 효목동", lat: 35.880411, lng: 128.642768},
        {label: "C", name: "북구 서변동", lat: 35.925667, lng: 128.594018},
        {label: "D", name: "서구 중리동", lat: 35.863822, lng: 128.540486},
    ];

    const bounds = new google.maps.LatLngBounds();

    spot.forEach(({ label, name, lat, lng}) => {
        const marker = new google.maps.Marker({
            position: { lat, lng },
            label,
            map: map,
        });
        bounds.extend(marker.position);
    });
    map.fitBounds(bounds);
}