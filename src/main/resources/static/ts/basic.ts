function test() {
  return "string for ts"
}

function submitBtn2() {
  // @ts-ignore
  axios.post('http://localhost:8080/register', {
    name: new User('rubok').name
  })
    .then((response) => {
      console.log(response)
    })
    .catch((error) => {
      console.error(error)
    })
}

class User {
  get name(): string {
    return this._name;
  }
  private readonly _name: string
  constructor(theName: string) { this._name = theName }
}
