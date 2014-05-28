String.prototype.contains = function(it) {
	return this.indexOf(it) != -1;
};

function printLog(msg) {
	console.log(msg);
}

var map;
var geocoder;

function initialize() {
	printLog("initialize");
	var mapOptions = {
		zoom : 2,
		center : new google.maps.LatLng(51.5167, 9.9167),
		mapTypeId : google.maps.MapTypeId.ROADMAP
	};

	map = new google.maps.Map(document.getElementById("map_canvas"), mapOptions);

	geocoder = new google.maps.Geocoder();

	addPlaces();
}

function addPlaces() {
	printLog("addPlaces");
	var grid = $(".placesGrid").first();
	var tiles = grid.find(".placesGridTile .rf-p-b");
	$
			.each(
					tiles,
					function(index, tile) {
						var infos = $(tile).find("tr");
						var address = "";
						var contentString = "";
						// TODO: iterate over span, prase address and info text
						$.each(infos, function(index2, info) {
							info = $(info);
							var label = info.find("label").text();
							var value = info.find("span").first().text();
							var id = info.find("span").first().attr("id");
							printLog(label + " " + value + " " + id);
							if (id.contains("placeLocation")) {
								address = value;
							}
						});

						geocoder
								.geocode(
										{
											"address" : address
										},
										function(results, status) {
											if (status == google.maps.GeocoderStatus.OK) {
												printLog("Geocode was successful");
												var marker = new google.maps.Marker(
														{
															map : map,
															position : results[0].geometry.location
														});

												var infowindow = new google.maps.InfoWindow(
														{
															content : contentString
														});

												google.maps.event
														.addListener(
																marker,
																'click',
																function() {
																	infowindow
																			.open(
																					map,
																					marker);
																});
											} else {
												printLog("Geocode was not successful for the following reason: "
														+ status);
											}
										});
					});
}

function addAutocomplete(id) {
	printLog("Add autocomplete for " + id);
	var autocomplete = new google.maps.places.Autocomplete(document
			.getElementById(id));
}

function loadMap() {
	printLog("loadMap");

	if (!document.getElementById("map_canvas")) {
		printLog("no map found");
		return;
	}

	printLog("Loading map");

	var script = document.createElement("script");
	script.type = "text/javascript";
	script.src = "http://maps.googleapis.com/maps/api/js?libraries=places&sensor=false&callback=initialize";
	document.getElementById("map_script").appendChild(script);
}