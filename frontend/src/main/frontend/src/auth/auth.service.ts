import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';

@Injectable()
export class AuthService{

	authenticated = false;

	constructor(
		private http: Http
		) {}

	authenticate(creds, callback) {
		var headers = creds ? this.makeHeaders(creds) : {};

		http.get('auth', {headers: headers})
			.subscribe(res => {
				this.authenticated = res.json().name ? true : false;
				callback && callback();
			});
	}

	private makeHeaders(creds){
		return { authorization: "Basic " + btoa(creds.username + ":" + creds.password)};
	}
}