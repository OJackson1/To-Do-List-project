document.querySelector("form.createTaskItemForm").addEventListener("submit", function (stop) {
    stop.preventDefault();

    let formElements = document.querySelector("form.createTaskItemForm").elements;
     console.log(formElements);
    let name=formElements["inputName"].value
    let taskId=formElements["inputId"].value

    createTask(name,taskId)
  });

  function createTask(name, taskId){
    fetch("http://localhost:8905/taskItem/create/", {
        method: 'post',
        headers: {
          "Content-type": "application/json"
        },
        body: json = JSON.stringify({
            "name": name,
            "task": {
                "taskId": taskId
            }
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