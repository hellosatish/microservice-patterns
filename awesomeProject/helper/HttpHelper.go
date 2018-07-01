package helper

import (
	"net/http"
	"log"
	"encoding/json"
	"bytes"
	"time"
	"errors"
)

type OperationBody interface {
}

//MakePostCall
func MakePostCall(urlToPost string, body OperationBody, headers map[string]string) (error, *http.Response) {
	log.Printf("In MakePostCall to %s with body %s",urlToPost,body)
	tr := &http.Transport{
		MaxIdleConns:       10,
		IdleConnTimeout:    30 * time.Second,
		DisableCompression: true,
	}
	var buffer bytes.Buffer
	encoder := json.NewEncoder(&buffer)
	//encoder.SetIndent(" ", "\t")
	err := encoder.Encode(body)
	if err != nil {
		log.Print("error while encoding "+err.Error())
	}

	log.Printf("Request body  %+v",buffer.String())

	client := &http.Client{Transport: tr}
	req, err := http.NewRequest(http.MethodPost, urlToPost, &buffer)
	if err != nil {
		log.Print("Error while creating the http request  " + err.Error())
		return errors.New("Error while creating http request  " + err.Error()), nil
	}

	req.Header.Add("Content-Type", "application/json")
	for key, value := range headers {
		req.Header.Add(key, value)
	}

	resp, err := client.Do(req)
	if err != nil {
		log.Print("Error while making post call " + err.Error())
		return errors.New("Error while making post call " + err.Error()), nil
	}
	log.Print("Successfull POST call with HTTP Status : " + resp.Status)

	return nil, resp
}


func MakePutCall(urlToPost string, body OperationBody, headers map[string]string) (error, *http.Response) {
	log.Printf("In MakePostCall to %s with body %s",urlToPost,body)
	tr := &http.Transport{
		MaxIdleConns:       10,
		IdleConnTimeout:    30 * time.Second,
		DisableCompression: true,
	}

	var buffer bytes.Buffer
	if body != nil{
		encoder := json.NewEncoder(&buffer)
		//encoder.SetIndent(" ", "\t")
		err := encoder.Encode(body)
		if err != nil {
			log.Print("error while encoding "+err.Error())
		}
		log.Printf("Prepared Request body  %+v",buffer.String())
	}

	client := &http.Client{Transport: tr}
	req, err := http.NewRequest(http.MethodPut, urlToPost, &buffer)
	if err != nil {
		log.Print("Error while creating the http request  " + err.Error())
		return errors.New("Error while creating http request " + err.Error()), nil
	}

	req.Header.Add("Content-Type", "application/json")
	for key, value := range headers {
		req.Header.Add(key, value)
	}

	resp, err := client.Do(req)
	if err != nil {
		log.Print("Error while making PUT call " + err.Error())
		return errors.New("Error while making PUT call " + err.Error()), nil
	}
	log.Print("Successfull POST call with HTTP Status : " + resp.Status)

	return nil, resp
}