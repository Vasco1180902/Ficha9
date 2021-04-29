/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* global fetch */

function pedir() {
    window.location.assign("/alunos");
}

function submeterForm() {
    let form = document.getElementById("formulario");
    let formdata = new FormData(form);

    fetch("/alunos", {
        method: "POST", body: formdata}).then((res) => {
        if (res.status === 200)
            return res.json();
        else
            throw Error("Erro no servidor!!");
    }).then((data) => {
        let li = "<tr><th>Numero</th><th>Nome</th><th>Email</th></tr>";
        li += "<tr><td>" + data.numero + "</td><td>" + data.nome + "</td><td>" + data.email + "</td><td>";
    }).catch((err) => console.log(err));



}
