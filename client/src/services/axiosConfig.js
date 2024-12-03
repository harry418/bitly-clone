import axios from 'axios';

const ENV_URL = `${window.location.protocol}://9000:`

const createAxiosInstance=(baseURL)=>{
    const instance = axios.create(
        baseURL = `${ENV_URL}${baseURL}`
    );
    instance.interceptors.request.use(
        (config)=>{return config},
        (error)=>{return Promise.reject(error)}
    )
    instance.interceptors.response.use(
        (config)=>{return config},
        (error)=>{return Promise.reject(error)}
    )
    return instance;
};
export default createAxiosInstance;