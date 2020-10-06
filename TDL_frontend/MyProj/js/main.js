fetch('http://localhost:8905/taskItem/read')
  .then(
    function(response) {
      if (response.status !== 200) {
        console.log('Looks like there was a problem. Status Code: ' +
          response.status);
        return;
      }

      // Examine the text in the responsez
      response.json().then(function(taskItemData) {
        console.log(taskItemData);

        let table = document.querySelector("table");
        let data = Object.keys(taskItemData[0]);

        createTableHead(table,data);
        createTableBody(table,taskItemData);
      });
    }
  )
  .catch(function(err) {
    console.log('Fetch Error :-S', err);
  });

  function createTableHead(table,data) {
      let tableHead = table.createTHead();
      let row = tableHead.insertRow();
    //   for (let keys of data){
    //       if(key == "itemTask"){
    //           console.log("skip");
    //       }else {
    //       let th = document.createElement("th");
    //       let text = document.createTextNode(keys);
    //       th.appendChild(text);
    //       row.appendChild(th)
    //       }
     // }
      let th2 = document.createElement("th")
      let text2 = document.createTextNode("View");
      th2.appendChild(text2);
      row.appendChild(th2);
  }

  function createTableBody(table,taskItemData) {
      for(let taskItemRecord of taskItemData){
          let row = table.insertRow();
          for (let values in taskItemRecord) {
              console.log("hello", values)
              if (values === "Item Task"){
                  console.log("skipped again");
                }else {
              console.log(taskItemRecord[values]);
              let cell = row.insertCell();
              let text = document.createTextNode(taskItemRecord[values]);
              cell.appendChild(text);
                }
          }
          let newCell = row.insertCell();
          let myViewButton = document.createElement("a");
          let myButtonValue = document.createTextNode("View");
          myViewButton.className = "btn btn-primary";
          myViewButton.href='record.html?id='+taskItemRecord.id 
          myViewButton.appendChild(myButtonValue);
          newCell.appendChild(myViewButton);
        
        let newCell2 = row.insertCell();
          let myDeleteButton = document.createElement("b");
          let myDeleteValue = document.createTextNode("Delete");
          myDeleteButton.className = "btn btn-danger"; 
          myDeleteButton.appendChild(myDeleteValue);
          newCell2.appendChild(myDeleteButton);
        }
  }