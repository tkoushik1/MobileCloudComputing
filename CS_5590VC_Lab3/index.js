$(document).ready(function() {
  $("#button").click(function() 
  {
      var input = $("#input").val();
      $("#log_search").val("");
      $.ajax
      ({
          type: "POST",
          url: "http://vhost1945.site1.compute.ihost.com:8080/CS590VC-T5-test/jaxrs/mypath/greeting/" + input,
          data: 'url',
          contentType: "application/json; charset=utf-8",
          dataType: "jsonp",
          success: function (data)
          {
            $("#log").val("Country:"+data.Country+","+"State:"+data.State+","+"City:"+data.city+","+ "Name:"+data.name);
            $.ajax
            ({
                type: "GET",
                url: "https://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=" + input,
                data: 'url',
                contentType: "application/json; charset=utf-8",
                dataType: "jsonp",
                success: function (data)
                {
                   $("#log_search").val(function(index, val) 
                   {
                      $.each(data.responseData.results,function(i,rows)
                      {
                         val = val + rows.url;
                         return false;                         
                      });
                       
                      return val;
                   });
                }
            }
             );
          }
      });
  }
                     );
});
                     