fetch('http://localhost:8905/task/readAll/')
  .then(
    function(response) {
      if (response.status !== 200) {
        console.log('Problem. Status Code: ' +
          response.status);
        return;
      }

      response.json().then(function(todoData) {
        console.log("data",todoData);

        // // console.log("this is me",myVar)
        // for (let abc of todoData){
        //   // console.log(abc)
        //   // console.log(todoData.taskItem[abc])
        //   for (values in abc){
        //     if(values =="taskItem"){
        //       console.log("abc",abc[values])
        //       for(let key of values){
        //         console.log(values[key])
        //         console.log(key)
        //       }
        //       }
          
        //     }
          
        // }
        
        for (let i=0;i<todoData.length;i++){
          let obj = todoData[i].taskItem
          // console.log(obj);
          for(let j=0;j<obj.length;j++){
            let obj2  =todoData[i].taskItem[j];
            // console.log(obj2)
            for(let key in obj2){
              console.log(obj2,obj2[key]);
            }
          }
        }
      
        

        let table = document.querySelector("table");
        let dataHead = Object.keys(todoData[1]);

        createTableBody(table, todoData);
      });
    }
  )
  .catch(function(err) {
    console.log('Fetch Error :-S', err);
  });

  function createTableBody(table, todoData) {
      for(let todoRecord of todoData) {
          let row = table.insertRow();
          for(values in todoRecord) {
            if(values === "taskItem") {
              
              for(let key in values){
            console.log("vin",values[key])
                // console.log("vg",todoData.values[key])
              }
            } else {
              console.log(todoRecord[values]);
              let cell = row.insertCell()
              let text = document.createTextNode(todoRecord[values]);
              cell.appendChild(text);
            }

          }
          
          let cellView = row.insertCell();
          let viewButton = document.createElement("a");
          let viewText = document.createTextNode("ADD");
          viewButton.className = "btn btn-info";
          viewButton.href = 'createATaskItem.html'
          viewButton.appendChild(viewText);
          cellView.appendChild(viewButton);
          
        let cellDell = row.insertCell();
        let DelButton = document.createElement("a");
        let DelText = document.createTextNode("Delete");
        DelButton.className = "btn btn-danger";
        DelButton.onclick = function(){
          deltodo(todoRecord.taskId);
          return false;
        };
        DelButton.appendChild(DelText);
        cellDell.appendChild(DelButton);
      }
  }

  function deltodo(id){
    console.log(id)
    console.log(typeof(id))
    fetch("http://localhost:8905/task/delete/"+id, {
        method: 'delete',
        headers: {
          "Content-type": "application/json"
        },
      })
      .then(function (data) {
        console.log('Request succeeded with JSON response', data);
        let deldiv = document.getElementById("deldiv");
        deldiv.className ="alert alert-danger"
        deldiv.textContent ="Task Deleted";
        window.location.reload();
        
      })
      .catch(function (error) {
        console.log('Request failed', error);
        let deldiv = document.getElementById("create");
        deldiv.className ="alert alert-success"
        deldiv.textContent ="Error deleting task";
      });
}