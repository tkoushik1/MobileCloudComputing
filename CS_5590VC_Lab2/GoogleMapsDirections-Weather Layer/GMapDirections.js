$("#GetDirectionsPage").on("pageshow", function (e) {
var directionDisplay;
var directionsService = new google.maps.DirectionsService();
var map;

function initialize() {
 directionsDisplay = new google.maps.DirectionsRenderer();
 var chicago = new google.maps.LatLng(41.850033, -87.6500523);
 var myOptions = {
   zoom:5,
   mapTypeId: google.maps.MapTypeId.ROADMAP,
   center: chicago
 }
 map = new google.maps.Map(document.getElementById("map_canvas"),
myOptions);
     var weather = new google.maps.weather.WeatherLayer({
        temperatureUnits: google.maps.weather.TemperatureUnit.DEGREE 
    });
 directionsDisplay.setMap(map);
    weather.setMap(map);
    var cloud = new google.maps.weather.CloudLayer();
    cloud.setMap(map);
directionsDisplay.setPanel(document.getElementById("directions")); 

}

function calcRoute() {
     var selectedMode = $("#mode").val();
var start = $("#start").val();
 var end = $("#end").val();
 var request = {
   origin:start,
   destination:end,
   travelMode: google.maps.DirectionsTravelMode[selectedMode]
 };
 directionsService.route(request, function(response, status) {
   if (status == google.maps.DirectionsStatus.OK) {
     directionsDisplay.setDirections(response);
       $("#results").show();
   }
 });
}
$(document).ready(function(){initialize(); $
("#end").change(calcRoute);$
("#start").change(calcRoute);$
("#mode").change(calcRoute);});
});

