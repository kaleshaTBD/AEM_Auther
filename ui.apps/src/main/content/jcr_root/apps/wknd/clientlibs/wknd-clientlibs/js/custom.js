function submit_giftcard_natural()
{
    var form = document.getElementById('giftcardNaturalForm');
    var data = new FormData(form);
    const data1 = {};
    for (const [key, value] of data.entries()) {
        data1[key] = value;
    }
    const jsonData = JSON.stringify(data1);
    console.log(jsonData);
      
      var xhr = new XMLHttpRequest();
      xhr.withCredentials = true;
      
      xhr.addEventListener("readystatechange", function() {
        if(this.readyState === 4) {
          console.log(this.responseText);
        }
      });
      
      xhr.open("POST", "http://localhost:4502/bin/post/servlet");
      xhr.setRequestHeader("Authorization", "Basic YWRtaW46YWRtaW4=");
      xhr.setRequestHeader("Content-Type", "application/json");
      xhr.setRequestHeader("Cookie", "cq-authoring-mode=TOUCH");
      
      xhr.send(jsonData);
}