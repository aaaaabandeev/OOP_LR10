
<html>
<head>
  <title>users</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
  <link href="https://fonts.googleapis.com/css?family=Josefin+Sans:100,200,300,medium,500,600,700,100italic,200italic,300italic,italic,500italic,600italic,700italic&display=swap" rel="stylesheet" />
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
  <link rel="stylesheet" href="../style/style.css">
  <style>
    .pricing-header {
      max-width: 700px;
    }
    .table-responsive{
      margin: 0 auto;
      max-width: 960px;
    }
    .form_songs{
      margin: 0 auto;
      max-width: 960px;
    }
  </style>
</head>
<body>
<script type="module">
  let request = new XMLHttpRequest();
  request.open("GET", "users.json");
  request.responseType = "json";
  request.send();
  request.onload = function() {
    let response = request.response;
    fillTable(response);
  }

  function fillTable(users) {
    let tbody = document.querySelector("tbody");
    flowers.forEach(user => {
      let tr = document.createElement("tr");
      tr.innerHTML = `
<th scope="row" class="text-start">${user.user_firstname}</th>
<td class="text-body-secondary">${user.user_lastname}</td>
<td class="text-body-secondary">${user.group}</td>
<td class="text-body-secondary">${user.age}</td>
<td class="text-body-secondary">${user.subject}</td>
`;
      tbody.appendChild(tr);
    });
  }
</script>

<div class="container py-3">
  <header>
    <div class="d-flex flex-column flex-md-row align-items-center pb-3 mb-4 border-bottom"><!--border-bottom-->
      <a href="${pageContext.request.contextPath}/prices" class="d-flex align-items-center link-body-emphasis text-decoration-none">
        <svg xmlns="http://www.w3.org/2000/svg" width="40" height="32" class="me-2" viewBox="0 0 118 94" role="img">
          <title>The songbook</title>
          <path fill-rule="evenodd" clip-rule="evenodd" d="M24.509 0c-6.733 0-11.715 5.893-11.492 12.284.214 6.14-.064 14.092-2.066 20.577C8.943 39.365 5.547 43.485 0 44.014v5.972c5.547.529 8.943 4.649 10.951 11.153 2.002 6.485 2.28 14.437 2.066 20.577C12.794 88.106 17.776 94 24.51 94H93.5c6.733 0 11.714-5.893 11.491-12.284-.214-6.14.064-14.092 2.066-20.577 2.009-6.504 5.396-10.624 10.943-11.153v-5.972c-5.547-.529-8.934-4.649-10.943-11.153-2.002-6.484-2.28-14.437-2.066-20.577C105.214 5.894 100.233 0 93.5 0H24.508zM80 57.863C80 66.663 73.436 72 62.543 72H44a2 2 0 01-2-2V24a2 2 0 012-2h18.437c9.083 0 15.044 4.92 15.044 12.474 0 5.302-4.01 10.049-9.119 10.88v.277C75.317 46.394 80 51.21 80 57.863zM60.521 28.34H49.948v14.934h8.905c6.884 0 10.68-2.772 10.68-7.727 0-4.643-3.264-7.207-9.012-7.207zM49.948 49.2v16.458H60.91c7.167 0 10.964-2.876 10.964-8.281 0-5.406-3.903-8.178-11.425-8.178H49.948z" fill="currentColor"></path>
        </svg>
        <span class="fs-4">The songbook</span>
      </a>

    </div>
  </header>
  <main>
    <h2 class="display-6 text-center mb-4">Add new flower</h2>

    <form class="form_songs" method="post">
      <div class="mb-3">
        <label for="user_firstname" class="form-label">user firstname</label>
        <input type="text" class="form-control" id="user_firstname" name="user_firstname">
      </div>
      <div class="mb-3">
        <label for="user_lastname" class="form-label">user lastname</label>
        <input type="text" class="form-control" id="user_lastname" name="user_lastname">
      </div>
      <div class="mb-3">
        <label for="group" class="form-label">group</label>
        <input type="text" class="form-control" id="group" name="group">
      </div>
      <div class="mb-3">
        <label for="age" class="form-label">age</label>
        <input type="text" class="form-control" id="age" name="age">
      </div>
      <div class="mb-3">
        <label for="subject" class="form-label">subject</label>
        <input type="text" class="form-control" id="subject" name="subject">
      </div>

      <button id = "myButton" class="btn btn-primary btn-lg" type="submit" onclick="">Add flower</button>

    </form>

    <p>

    </p>

    <div class="table-responsive">
      <table class="table text-center">
        <thead>
        <tr>
          <th style="width: 34%;">Name</th>
          <th style="width: 34%;">Sort</th>
          <th style="width: 22%;">Color</th>
          <th style="width: 22%;">Live</th>
          <th style="width: 22%;">Red book</th>
        </tr>
        </thead>
        <tbody>
        <tr>
          <th scope="row" class="text-start">Петр</th>
          <td class="text-body-secondary">Петров</td>
          <td class="text-body-secondary">1</td>
          <td class="text-body-secondary">20</td>
          <td class="text-body-secondary">математика</td>
        </tr>
        </tbody>
      </table>
    </div>
  </main>

  <footer >
    <div class="row">
      <div class="col-12 col-md">

      </div>
    </div>
  </footer>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
