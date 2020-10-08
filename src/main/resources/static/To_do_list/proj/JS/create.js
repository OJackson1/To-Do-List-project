document.querySelector("form.createTaskForm").addEventListener("submit", function (stop) {
    stop.preventDefault();

    let formElements = document.querySelector("form.createTaskForm").elements;
     console.log(formElements);
    let name=formElements["inputName"].value
    let taskItemId=formElements["inputId"].value

    createTask(name,taskItemId)
  });

  function createTask(name, taskItemId){
    fetch("http://localhost:8905/taskItem/create/", {
        method: 'POST',
        headers: {
          "Content-type": "application/json"
        },
        body: json = JSON.stringify({
            "name": name,
            "taskItem": {
                "taskItemId": taskItemId
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