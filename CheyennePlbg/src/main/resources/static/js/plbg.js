window.addEventListener("load", function(){
console.log('script loaded');
  init();
});

function init() {
  console.log('in init');
  document.findAll.findAllSubmit.addEventListener('click', findAll);
  // document.newJob.createJob.addEventListener('click', createJob);
  document.jobLookup.lookup.addEventListener('click', lookUpJobByID);
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
          console.log(job.title);
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
}

let findAll = function(e){
  e.preventDefault();
}

let deleteJob = function(e){
  e.preventDefault();
}

function displayJob(job){
  var resultData = document.getElementById('resultBody');
  resultData.textContent = '';
  let h1 = document.createElement('h1');
  h1.textContent = job.name;
  let blockquote = document.createElement('blockquote');
  blockquote.textContent = job.description;
}