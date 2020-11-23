window.addEventListener("load", function(){
console.log('script loaded');
  init();
});

function init() {
  console.log('in init');
  document.findAll.findAllSubmit.addEventListener('click', findAll);
  document.newJob.createJob.addEventListener('click', createJob);
  document.jobLookup.lookup.addEventListener('click', lookUpJobByID);
  document.update.updateJob.addEventListener('click', updateJobByID);
  document.update.deleteJob.addEventListener('click', deleteJobByID);
}





let updateJobByID = function(e){
  e.preventDefault();
  var job = document.update.updateJob.value;

}





let lookUpJobByID = function(e){
  e.preventDefault();
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
          var dataDiv = document.getElementById('filmData');
          dataDiv.textContent = 'Film Not Found';
        }
      }
    }
    xhr.send();
  }
}


let createJob = function(e){
  e.preventDefault();
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



let deleteJob = function(e){
  e.preventDefault();
}

function displayJobs(jobs){
   
  var resultData = document.getElementById('resultBody');
  resultData.textContent = '';
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
  updateBtn.name = 'updateJob';
  updateBtn.textContent = "Update Job";
  updateBtn.value = jobs[i];
  deleteBtn.name = 'deleteJob';
  deleteBtn.textContent = "Delete Job";
  deleteBtn.value = jobs[i];
  update.appendChild(updateBtn);
  update.appendChild(deleteBtn);
  resultData.appendChild(h1);
  resultData.appendChild(update);
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
    updateBtn.value = job;
    updateBtn.textContent = "Update Job";
    deleteBtn.name = 'deleteJob';
    deleteBtn.textContent = "Delete Job";
    deleteBtn.value = job;
    update.appendChild(updateBtn);
    update.appendChild(deleteBtn);

  resultData.appendChild(h1);
  resultData.appendChild(update);
  
}