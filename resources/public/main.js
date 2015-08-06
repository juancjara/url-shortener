var post = function(url, data, cb) {
  request('POST', url, data, cb);
};

var request = function (method, url, data, cb) {
    var xmlhttp = new XMLHttpRequest();;
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState == XMLHttpRequest.DONE ) {
            if(xmlhttp.status == 200){
                cb(null, xmlhttp.responseText);
            }
            else if(xmlhttp.status == 400) {
                cb('err');
            }
            else {
                cb('err');
            }
        }
    }

    xmlhttp.open(method, url);
    xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    xmlhttp.send(data);
};

var serializeForm = function(form) {
  var values = [].map
    .call(form.childNodes, function(e) {
      return e.name + '=' + encodeURIComponent(e.value);
    });
  values.pop();
  return values.join('&');
};

var form = document.querySelector('form');
var response = document.getElementById('short-url');

form.addEventListener('submit', function(e) {
  e.preventDefault();
  post('/short', serializeForm(e.target), function(err, res) {
      response.textContent = 'Your shorter url is: ' + res;
  });
});
