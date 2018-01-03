import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';

@Injectable()
export class AuthService{

	authenticated = false;

	constructor(
		private http: Http
		) {}

	authenticate(creds, callback) {
		var options = new RequestOptions({
			headers: creds ? this.makeHeaders(creds) : new Headers()
		});

		this.http.get('auth', options)
			.subscribe(res => {
				this.authenticated = res.json().name ? true : false;
				callback && callback();
			});
	}

	private makeHeaders(creds){
		return new Headers({ authorization: "Basic " + btoa(creds.username + ":" + creds.password)});
	}
}