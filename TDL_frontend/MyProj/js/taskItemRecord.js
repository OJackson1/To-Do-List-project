const params = new URLSearchParams(window.location.search);

for (let param of params){
    console.log("Hello",param)
    let id = param[1];
    console.log(id);
    getSingleTask(id);
}

function getSingleTask(id){
fetch('http://localhost:8905/taskItem/read/'+id)
  .then(
    function(response) {
      if (response.status !== 200) {
        console.log('Looks like there was a problem. Status Code: ' +
          response.status);
        return;
      }

      // Examine the text in the response
      response.json().then(function(data) {
        console.log(data);

        document.getElementById("taskItemId").value=data.id
        document.getElementById("taskItemName").value=data.name
        document.getElementById("task").value=data.body
      });
    }
  )
  .catch(function(err) {
    console.log('Fetch Error :-S', err);
  });
}

document.querySelector("form.taskItemRecord").addEventListener("submit",function(stop){
stop.preventDefault();

let formElements = document.querySelector("form.taskItemRecord").elements;
  //console.log(formElements);
 let id = parseInt(formElements["taskItemId"].value);
 let strings = formElements["name"].value;
 let body = formElements["task"].value;

  console.log(id);
  console.log(name);
  updateTaskItem(id,name,body)
})

function updateTaskItem(id,name,body){

 let updateID=parseInt(id);

 console.log(updateID);


  fetch("http://localhost:8905/taskItem/update/"+updateid, {
    method: 'PUT',
    headers: {
      "Content-type": "application/json"
    },
    body:json = JSON.stringify({
      "name": "Tom",
      "strings": 12,
      "type": "lead",
      "room": {
      "id":3
      },
      "id":updateid
     })
  })
  .then(res => res.json)
  .then(function (data) {
    console.log('Request succeeded with JSON response', data);
  })
  .catch(function (error) {
    console.log('Request failed', error);
  });
}