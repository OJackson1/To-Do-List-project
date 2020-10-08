document.querySelector("form.createATaskForm").addEventListener("submit", function (stop) {
    stop.preventDefault();

    let formElements = document.querySelector("form.createATaskForm").elements;
     console.log(formElements);
    let name=formElements["inputName"].value

    createTask(name)
  });

  function createTask(name){
    fetch("http://localhost:8905/task/create/", {
        method: 'POST',
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