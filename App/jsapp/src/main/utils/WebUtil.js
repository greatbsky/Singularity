//Web相关的util方法
export default class {

    static fetchHandler(url, opt, thenFn, successCB, failCB) {
        return fetch(url, opt).then(thenFn).then((data) => {successCB(data)}).catch((err) => failCB(err));
    }

    static get(url, successCB, failCB) { //web get method, request fetch
        var opt = {
            method: 'GET'
        };
        return this.fetchHandler(url, opt, (response) => response.text(), successCB, failCB);
    }

    /**
        param: a json object
    */
    static getJSON(url, successCB, failCB) { //web getJSON method, request fetch
        var opt = {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
        };
        return this.fetchHandler(url, opt, (response) => response.json(), successCB, failCB);
    }

    static getJSONS(url, successCB, failCB) { //web getJSON method, request fetch
        var opt = {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
        };
        return this.fetchHandler(url, opt, (response) => response.json(), successCB, failCB);
    }

    static post(url, param, successCB, failCB) { //web post method, request fetch
        var opt = {
            method: 'POST',
            headers: {
             },
            body: param
        };
        return this.fetchHandler(url, opt, (response) => response.text(), successCB, failCB);
    }
    // fetchWithTimeout (timeout, ...args) {
    //     return Promise.race([fetch(...args), delay(timeout)])
    // }
}
