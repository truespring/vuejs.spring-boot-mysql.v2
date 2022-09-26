function ex() {
  return "string"
}

function submitBtn() {
  axios.post('http://localhost:8080/register', {
    name: 'rubok'
  })
    .then((response) => {
      console.log(response)
    })
    .catch((error) => {
      console.error(error)
    })
}
