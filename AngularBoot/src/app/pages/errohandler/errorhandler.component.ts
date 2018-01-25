/**
 *  Error handler for web service caller
 */

export interface ErroHandler {
    handleError(error: any): Promise<any>;
}

export class WebserviceErrorHandler implements ErroHandler {

    constructor() {
    }

    handleError(error: any): Promise<any> {
        if (error.status === 0) {
            alert('WebserviceErrorHandler :Backend Server is Down, Please Start Server and then try Again' + '\n' + 'Server Details\t:');
        } else if (error.status === 500) {
            console.error('Internal Server Error', error); // for demo purposes only
        } else if (error.status === 400) {
            console.error('BAD request', error); // for demo purposes only
        } else if (error.status === 405) {
            console.error('HTTP verb used to access this page is not allowed', error); // for demo purposes only
        } else if (error.status === 406) {
            console.error('Not Acceptable', error); // for demo purposes only
        }  else if (error.status === 407) {
            console.error('Proxy authentication required', error); // for demo purposes only
        } else if (error.status === 409) {
            console.error('Conflict', error); // for demo purposes only
        } else if (error.status === 415) {
            console.error(' Unsupported media type', error); // for demo purposes only
        } else if (error.status === 406) {
            console.error('Not Acceptable', error); // for demo purposes only
        } else {
            console.error('An error occurred', error); // for demo purposes only
        }
        return Promise.reject(error.message || error);
    }
}
