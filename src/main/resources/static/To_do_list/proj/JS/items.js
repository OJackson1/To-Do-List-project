fetch('http://localhost:8905/task/readAll/')
  .then(
    function(response) {
      if (response.status !== 200) {
        console.log('Problem. Status Code: ' +
          response.status);
        return;
      }

      response.json().then(function(taskItemData) {
        console.log("data",taskItemData);

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
        
        for (let i=0;i<taskItemData.length;i++){
          let obj = taskItemData[i].taskItem
          // console.log(obj);
          for(let j=0;j<obj.length;j++){
            let obj2  =taskItemData[i].taskItem[j];
            // console.log(obj2)
            for(let key in obj2){
              console.log(obj2,obj2[key]);
            }
          }
        }
      
        

        let table = document.querySelector("table");
        let dataHead = Object.keys(taskItemData[0]);


        createTableBody(table, taskItemData);
        createTableHead(table,data);
        
      });
    }
  )
  .catch(function(err) {
    console.log('Fetch Error :-S', err);
  });

  function createTableHead(table,data){
    let tableHead= table.createTHead();
    let row = tableHead.insertRow();
    for(let keys of data){
      if(keys === "task"){
        console.log("skip");
      }else{
        let th = document.createElement("th");
        let text = document.createTextNode(keys);
        th.appendChild(text);
        row.appendChild(th)
      }
    }
    let th2 = document.createElement("th")
    let text2 = document.createTextNode("View");
    th2.appendChild(text2);
    row.appendChild(th2);
    let th3 = document.createElement("th")
    let text3 = document.createTextNode("Delete");
    th3.appendChild(text3);
    row.appendChild(th3);
}
  function createTableBody(table, taskItemData) {
      for(let taskItemRecord of taskItemData) {
          let row = table.insertRow();
          for(values in taskItemRecord) {
            if(values === "taskItem") {
              
              for(let key in values){
            console.log("vin",values[key])
              }
            } else {
              console.log(taskItemRecord[values]);
              let cell = row.insertCell()
              let text = document.createTextNode(taskItemRecord[values]);
              cell.appendChild(text);
            }

          }
          
          let cellView = row.insertCell();
          let viewButton = document.createElement("a");
          let viewText = document.createTextNode("ADD/UPDATE");
          viewButton.className = "btn btn-info";
          viewButton.href = 'createATaskItem.html'
          viewButton.appendChild(viewText);
          cellView.appendChild(viewButton);
          
        let cellDell = row.insertCell();
        let DelButton = document.createElement("a");
        let DelText = document.createTextNode("Delete");
        DelButton.className = "btn btn-danger";
        DelButton.onclick = function(){
          delTaskItem(taskItemRecord.taskId);
          return false;
        };
        DelButton.appendChild(DelText);
        cellDell.appendChild(DelButton);
      }
  }

  function delTaskItem(id){
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
        deldiv.textContent ="Error deleting task Item";
      });
}