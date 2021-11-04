hash = document.location.hash;
token = "";
if(hash){
    token = /access_token=([^&]+)/.exec(document.location.hash)[1];
}
if(ctx && token) {
    let uri = (ctx + '/signIn?access_token=' + token);
    document.location.replace(uri);
}
