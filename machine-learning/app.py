from flask import Flask, render_template, request, redirect, url_for, session, app
from datetime import timedelta
import string
from keras.models import load_model
import cv2
import numpy as np
from keras.preprocessing.image import img_to_array
from keras.preprocessing import image

import urllib.request
import requests
from PIL import Image


app = Flask(__name__)
app.config['SECRET_KEY'] = b'_5#y2L"F4Q8z\n\xec]/'

@app.before_request
def make_session_permanent():
    session.permanent = True
    app.permanent_session_lifetime = timedelta(minutes=5)

@app.route("/", methods=["GET", "POST"])

def home():
    CLASSES = ["NORMAL","PNEUMONIA"]
    urllib.request.urlretrieve(
    'https://storage.googleapis.com/pneumonia-alvex/assets/modelsaved/model-gcp3.h5', "gfg.h5")

    newmodel = load_model('gfg.h5')

    data = request.form['url']

    urllib.request.urlretrieve(
    data,"gfg.png")

    img_arr = cv2.imread('gfg.png', 0)
    img_arr = cv2.resize(img_arr, (224,224))
    img_arr = img_arr / 255.0
    img_arr = img_arr.reshape(-1, 224, 224, 1)
    prediction = newmodel.predict(img_arr)
    hasilpred = "Normal" if (prediction <=0.5).all() else "Pneumonia"
    print(hasilpred)
    return {'status': True, 'message': 'success', 'data': {'prediction': hasilpred}}


def shutdown_server():
    func = request.environ.get('werkzeug.server.shutdown')
    if func is None:
        raise RuntimeError('Not running with the Werkzeug Server')
    func()
    
@app.route('/shutdown', methods=['GET'])
def shutdown():
    shutdown_server()
    return 'Server shutting down...'

if __name__ == "__main__":
    app.run(host='0.0.0.0', port=5000) 
