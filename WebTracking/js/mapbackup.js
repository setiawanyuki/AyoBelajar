  	var database = firebase.database();
	var lat;
	var lng;
    firebase.database().ref().on('value', function(snapshot) {
    var finaldata = JSON.stringify(snapshot.val());
    console.log(finaldata);

    finaldata = finaldata.replace(/"/g, "'");
    var ultrafinal = JSON.stringify(eval("(" + finaldata + ")"));
    var obj = JSON.parse(ultrafinal);
	var Time = obj.location.coordinates[2];
	lat = obj.location.coordinates[0];
	lng = obj.location.coordinates[1];
	console.log(obj.location.coordinates[0]);
	console.log(obj.location.coordinates[1]);
	});

	/*preRef.on("value", function(snap) {
	 document.getElementById("namaAnak").innerText = snap.child("nama_anak").val();
	 document.getElementById("umurAnak").innerText = snap.child("umur").val();
	});*/ 

	/*rootRef.once("value", function(snapshot){
	 snapshot.forEach(function(snap){
	  console.log(snap.key);
	  var val = snap.val();
	  lat = val.latitude;
	  lng = val.longitude;
	  console.log(lat);
	  console.log(lng);
	 });
	});*/


	var marker;
	setInterval(showPosition(),10000);
	// every 10 seconds

	
	function myMap() {
		var pos = new google.maps.LatLng(lat, lng);
		var mapOptions = {
		    center: new google.maps.LatLng(6.18, 106.83),
		    zoom: 5,
		    mapTypeId: google.maps.MapTypeId.HYBRID
		}
		var map = new google.maps.Map(document.getElementById("map"), mapOptions);
		var marker = new google.maps.Marker({
          position: pos,
          map: map,
          title: 'Hello World!'
        });
	}


	var x = document.getElementById("map");
	function getLocation() {
    if (navigator.geolocation) {
        showPosition();
    } else {
        x.innerHTML = "Geolocation is not supported by this browser.";
	    }
	}

	function showPosition() {
	    
		var mapOptions = {
		    center: new google.maps.LatLng(lat, lng),
		    zoom: 15,
		    mapTypeId: google.maps.MapTypeId.HYBRID
		};
		var map = new google.maps.Map(document.getElementById("map"), mapOptions);

		var pos = new google.maps.LatLng(lat, lng);
		var	marker = new google.maps.Marker({
			position:pos,
			animation: google.maps.Animation.BOUNCE
		});
 		marker.setMap(map);
		setInterval(updateMarker(marker),10000);

		function updateMarker(marker){
			marker = [];
			var myLatLng = {lat: lat, lng: lng};
			marker = new google.maps.Marker({
				position: myLatLng,
				map: map,
				animation: google.maps.Animation.BOUNCE
			});
			marker.setMap(map);
		}
	}
	
	
	//To use this code on your website, get a free API key from Google.
	//Read more at: https://www.w3schools.com/graphics/google_maps_basic.asp

	function showError(error) {
	    switch(error.code) {
	        case error.PERMISSION_DENIED:
	            x.innerHTML = "User denied the request for Geolocation."
	            break;
	        case error.POSITION_UNAVAILABLE:
	            x.innerHTML = "Location information is unavailable."
	            break;
	        case error.TIMEOUT:
	            x.innerHTML = "The request to get user location timed out."
	            break;
	        case error.UNKNOWN_ERROR:
	            x.innerHTML = "An unknown error occurred."
	            break;
	    }
	}
