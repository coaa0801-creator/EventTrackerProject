window.addEventListener("load", function(){
console.log('script loaded');
let xhr = new XMLHttpRequest();
xhr.open('GET', 'api/jobs/', true);
xhr.onreadystatechange = function() {
  if (xhr.readyState === 4) {
    console.log('Request Complete.');
    if (xhr.status === 200) {
      console.log('request Successful');
      var jobs = JSON.parse(xhr.responseText);
      let count = jobs.length;
      console.log(count);
      let numJobs = document.getElementById('numInList');
      numJobs.textContent = "(" + count + " jobs in your list)";
      } else {
      console.error('request failed ' + xhr.status);
      var dataDiv = document.getElementById('ResultBody');
      dataDiv.textContent = 'No Jobs Found';
    }
  }

}
xhr.send();
  init();
});

function init() {
  console.log('in init');
  document.findAll.findAllSubmit.addEventListener('click', findAll);
  document.newJob.createJob.addEventListener('click', createJob);
  document.jobLookup.lookup.addEventListener('click', lookUpJobByID);
}



let deleteJobByID = function(e){
  e.preventDefault();
  window.location = window.location;
  var dataDiv = document.getElementById('resultBody');
  var response = confirm("Are you sure you'd like to delete");
  if(response){
    var jobId = e.target.value;
    console.log(jobId);
    let xhr = new XMLHttpRequest();
    xhr.open('DELETE', 'api/jobs/' + jobId, true);
    xhr.onreadystatechange = function() {
      if (xhr.readyState === 4) {
        console.log('Request Complete.');
        if (xhr.status === 204) {
          console.log('request Successful');          
          dataDiv.textContent = '';
        } else {
          console.error('request failed ' + xhr.status);
          dataDiv.textContent = 'Job Not Deleted';
        }
      }
    }
    xhr.send();
    
  }
  
}




let updateJobByID = function(e){
  e.preventDefault();
  window.location = window.location;
  var resultData = document.getElementById('resultBody');
  resultData.textContent = '';

  var jobId = e.target.value;
  if (!isNaN(jobId) && jobId > 0) {
    let xhr = new XMLHttpRequest();
    xhr.open('GET', 'api/jobs/' + jobId, true);
    xhr.onreadystatechange = function() {
      if (xhr.readyState === 4) {
        console.log('Request Complete.');
        if (xhr.status === 200) {
          console.log('request Successful');
          let job = JSON.parse(xhr.responseText);
          console.log(job.name);
          let updateForm = document.createElement('form');
          updateForm.name = "updateForm"
          let nameInput = document.createElement('input');
          nameInput.type = "text";
          nameInput.name = "name";
          nameInput.value = job.name;
          updateForm.appendChild(nameInput);
          let estimateInput = document.createElement('input');
          estimateInput.type = "text";
          estimateInput.name = "estimate";
          estimateInput.value = job.estimate;
          updateForm.appendChild(estimateInput);
          let paidInput = document.createElement('input');
          paidInput.type = "text";
          paidInput.name = "paid";
          var zero = 0;
          if(job.paid === zero) {
            paidInput.value = "Un-Paid";
          }else{
            paidInput.value = "Paid";
          };
          updateForm.appendChild(paidInput);
          var submit = document.createElement("button");
          submit.name = "submit";
          submit.type = "submit";
          submit.value = e.target.value;
          submit.textContent = "Save";
          updateForm.appendChild(submit);
          let h1 = document.createElement('h1');
          h1.textContent = "Update " + job.name + " job";
          resultData.appendChild(h1);
          resultData.appendChild(updateForm);
          submit.addEventListener('click', saveJobUpdate);
        
          
        } else {
          console.error('request failed ' + xhr.status);
          
        }
      }
    }
    xhr.send();
  }
  console.log(jobId);
  
};

let saveJobUpdate = function(e){
e.preventDefault();
window.location = window.location;
let xhr = new XMLHttpRequest();
let jobId = e.target.value;
  xhr.open('PUT', 'api/jobs/' + jobId , true);
  xhr.setRequestHeader("Content-type", "application/json");
  xhr.onreadystatechange = function() {
    if (xhr.readyState === 4) {
      console.log('Request Complete.');
      if (xhr.status === 201) {
        document.newJob.reset();
        let newJob = JSON.parse(xhr.responseText);
        // console.log(newFilm.title);
        displayJob(newJob);
      } else {
        console.log("POST request failed.");
        console.error(xhr.status + ': ' + xhr.responseText);
      }
    }
  };
  let paid = 0;
if (e.target.parentElement.paid.value === "Paid"){
  let paid = 1;
};

  var userJob = {
    name: e.target.parentElement.name.value,
    estimate: e.target.parentElement.estimate.value,
    paid: paid
  };
  var userObjectJSON = JSON.stringify(userJob);
  xhr.send(userObjectJSON);
};











let lookUpJobByID = function(e){
  e.preventDefault();
  window.location = window.location;
  var jobId = document.jobLookup.jobId.value;
  if (!isNaN(jobId) && jobId > 0) {
    let xhr = new XMLHttpRequest();
    xhr.open('GET', 'api/jobs/' + jobId, true);
    xhr.onreadystatechange = function() {
      if (xhr.readyState === 4) {
        console.log('Request Complete.');
        if (xhr.status === 200) {
          console.log('request Successful');
          let job = JSON.parse(xhr.responseText);
          console.log(job.name);
          displayJob(job);
          
        } else {
          console.error('request failed ' + xhr.status);
          var dataDiv = document.getElementById('resultBody');
          dataDiv.textContent = 'Job Not Found';
        }
      }
    }
    xhr.send();
  }
}


let createJob = function(e){
  e.preventDefault();
  window.location = window.location;
  let xhr = new XMLHttpRequest();
  xhr.open('POST', 'api/jobs/', true);
  xhr.setRequestHeader("Content-type", "application/json");
  xhr.onreadystatechange = function() {
    if (xhr.readyState === 4) {
      console.log('Request Complete.');
      if (xhr.status === 201) {
        document.newJob.reset();
        let newJob = JSON.parse(xhr.responseText);
        // console.log(newFilm.title);
        displayJob(newJob);
      } else {
        console.log("POST request failed.");
        console.error(xhr.status + ': ' + xhr.responseText);
      }
    }
  };
  var userJob = {
    name: document.newJob.name.value,
    estimate: document.newJob.estimate.value,
  };
  var userObjectJSON = JSON.stringify(userJob);
  xhr.send(userObjectJSON);
};

  


let findAll = function(e){

  console.log("in find All");
  e.preventDefault();
  let xhr = new XMLHttpRequest();
  xhr.open('GET', 'api/jobs/', true);
  xhr.onreadystatechange = function() {
    if (xhr.readyState === 4) {
      console.log('Request Complete.');
      if (xhr.status === 200) {
        console.log('request Successful');
        let jobs = JSON.parse(xhr.responseText);
        console.log(jobs.length);
        displayJobs(jobs);
      } else {
        console.error('request failed ' + xhr.status);
        var dataDiv = document.getElementById('ResultBody');
        dataDiv.textContent = 'No Jobs Found';
      }
    }
  }
  xhr.send();
}


function displayJobs(jobs){
  var resultData = document.getElementById('resultBody');
  
  resultData.textContent = '';
  var count = 1;
  for(let i = 0; i < jobs.length; i++){
    console.log(jobs[i].name);
    let h1 = document.createElement('h1');
    h1.textContent = jobs[i].name + ": "
    if(jobs[i].address !== null){
      h1.textContent += jobs[i].address.address + " " + jobs[i].address.city + ", " + jobs[i].address.state;}
      if(jobs[i].customer !== null){
        let blockquote = document.createElement('blockquote');
        blockquote.textContent = jobs[i].customer.firstName;}
        let update = document.createElement('form');
        let updateBtn = document.createElement('button');
        let deleteBtn = document.createElement('button');
        updateBtn.id = 'updateJob';
        updateBtn.textContent = "Update Job";
        updateBtn.value = jobs[i].id;
        deleteBtn.id = 'deleteJob';
        deleteBtn.textContent = "Delete Job";
        deleteBtn.value =  jobs[i].id;
        update.appendChild(updateBtn);
        update.appendChild(deleteBtn);
        resultData.appendChild(h1);
        resultData.appendChild(update);
        updateBtn.addEventListener('click', updateJobByID);
        deleteBtn.addEventListener('click', deleteJobByID);
      }
    }
    
    
    function displayJob(job){
      console.log(job.name);
      var resultData = document.getElementById('resultBody');
      resultData.textContent = '';
      let h1 = document.createElement('h1');
      h1.textContent = job.name + ": "
      if(job.address !== null){
        h1.textContent += job.address.address + " " + job.address.city + ", " + job.address.state;}
        if(job.customer !== null){
          let blockquote = document.createElement('blockquote');
          blockquote.textContent = job.customer.firstName;}
          let update = document.createElement('form');
          update.name = 'update';
          let updateBtn = document.createElement('button');
          let deleteBtn = document.createElement('button');
          updateBtn.name = 'updateJob';
          updateBtn.value = job.id;
          updateBtn.textContent = "Update Job";
          deleteBtn.id = 'deleteJob';
          deleteBtn.textContent = "Delete Job";
          deleteBtn.value = job.id;
          update.appendChild(updateBtn);
          update.appendChild(deleteBtn);
          
          resultData.appendChild(h1);
          resultData.appendChild(update);
          updateBtn.addEventListener('click', updateJobByID);
          deleteBtn.addEventListener('click', deleteJobByID);
  
}