hash = document.location.hash;
token = "";
if(hash){
    token = /access_token=([^&]+)/.exec(document.location.hash)[1];
    time = /expires_in=([0-9]+)/.exec(document.location.hash)[1];
}
if(ctx && token && time) {
    let uri = (ctx + '/signIn?access_token=' + token + "&expires_in=" + time);
    document.location.replace(uri);
}
