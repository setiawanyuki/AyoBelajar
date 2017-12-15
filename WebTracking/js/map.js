  	var database = firebase.database();
	var lat;
	var lng;
    firebase.database().ref().on('value', function(snapshot) {
    var finaldata = JSON.stringify(snapshot.val());
    //console.log(finaldata);

    finaldata = finaldata.replace(/"/g, "'");
    var ultrafinal = JSON.stringify(eval("(" + finaldata + ")"));
    var obj = JSON.parse(ultrafinal);
	var Time = obj.location.coordinates[2];
	lat = obj.location.coordinates[0];
	lng = obj.location.coordinates[1];
	console.log(obj.location.coordinates[0]);
	console.log(obj.location.coordinates[1]);
	});

	var marker = null;
	var map;
	var x = document.getElementById("map");
	
	function getLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(showPosition, showError);
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
		map = new google.maps.Map(document.getElementById("map"), mapOptions);

		var pos = new google.maps.LatLng(lat, lng);
			marker = new google.maps.Marker({
			position:pos,
			animation: google.maps.Animation.BOUNCE
		});
 		marker.setMap(map);
	}
	
	
	function autoUpdate() {
	  navigator.geolocation.getCurrentPosition(function(position) {  
		var newPoint = new google.maps.LatLng(lat, lng);

		if (marker) {
		  // Marker already created - Move it
		  marker.setPosition(newPoint);
		}
		else {
		  // Marker does not exist - Create it
			marker = new google.maps.Marker({
			position: newPoint,
			map: map
		  });
		}

		// Center the map on the new position
		map.setCenter(newPoint);
	  }); 

	  // Call the autoUpdate() function every 5 seconds
	  setTimeout(autoUpdate, 10000);
	}
	
	autoUpdate();

	
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
