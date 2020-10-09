document.querySelector("form.createTaskItemForm").addEventListener("submit", function (stop) {
    stop.preventDefault();

    let formElements = document.querySelector("form.createATaskItemForm").elements;
     console.log(formElements);
    let name=formElements["inputName"].value
   

    createTaskItem(name)
  });

  function createTaskItem(name){
    fetch("http://localhost:8905/taskItem/create/", {
        method: 'Post',
        headers: {
          "Content-type": "application/json"
        },
        body: json = JSON.stringify({
            "name": name,
        })
      })
      .then(json)
      .then(function (data) {
        console.log('Request succeeded with JSON response', data);
      })
      .catch(function (error) {
        console.log('Request failed', error);
      });
  }