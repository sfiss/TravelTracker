function printLog(msg) {
	console.log(msg);
}

function initialize() {
	printLog('initialize');
	var mapOptions = {
		zoom : 2,
		center : new google.maps.LatLng(51.5167, 9.9167),
		mapTypeId : google.maps.MapTypeId.ROADMAP
	};

	var map = new google.maps.Map(document.getElementById("map_canvas"),
			mapOptions);
}

function loadMap() {
	printLog('loadMap');

	if (!document.getElementById("map_canvas")) {
		printLog('no map found');
		return;
	}

	printLog('Loading map');

	var script = document.createElement("script");
	script.type = "text/javascript";
	// key=AIzaSyAhYMgeFOnA3ZQM4BV3HGCSKdep3LClSRA&
	script.src = "http://maps.googleapis.com/maps/api/js?sensor=false&callback=initialize";
	document.getElementById("scripts").appendChild(script);
}