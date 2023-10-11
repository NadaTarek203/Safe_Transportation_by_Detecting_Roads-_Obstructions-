from flask import Flask, request, jsonify
from PIL import Image
import io
#import detect_ahmed
import os
from flask_cors import CORS,cross_origin
from io import BytesIO
#import cv2
import base64
import torch.backends.cudnn as cudnn
import pandas as pd
import os
import torch
import argparse
from token import ASYNC
import pandas as pd
import time
from playsound import playsound
from keras.models import model_from_json
from sklearn.preprocessing import StandardScaler

app = Flask(__name__)
#cors = CORS(app)
#app.config['CORS_HEADERS'] = 'Content-Type'
# model=torch.load("yolov5/yolov5x.pt",map_location='cpu')
@app.after_request
def add_cors_headers(response):
    response.headers['Access-Control-Allow-Origin'] = '*'
    response.headers['Access-Control-Allow-Headers'] = 'Content-Type,Authorization'
    response.headers['Access-Control-Allow-Methods'] = 'GET,PUT,POST,DELETE,OPTIONS'
    return response
# model= torch.hub.load("./", "custom",path="yolov5x.pt",source="local")
model= torch.hub.load("./", "custom",path="last_7.pt",source="local")

#model = torch.hub.load("ultralytics/yolov5", "yolov5s")
# di=distance_estimation(labels,data,r'F:\4th 2\GP\distance_estmation\vehicle-distance-estimationV2\distance-estimator\models\model@1535477330.json',r'F:\4th 2\GP\distance_estmation\vehicle-distance-estimationV2\distance-estimator\models\model@1535477330.h5',r'F:\4th 2\GP\distance_estmation\vehicle-distance-estimationV2\distance-estimator')
json_file = open(r'D:\fcis\Last Year\Graduation Project\backend-distanceEstimator-main\vehicle-distance-estimationV2\distance-estimator\models\model@1535477330.json', 'r')
loaded_model_json = json_file.read()
json_file.close()
loaded_model = model_from_json(loaded_model_json)
loaded_model.compile(loss='mean_squared_error', optimizer='adam')
# load weights into new model
loaded_model.load_weights(r'D:\fcis\Last Year\Graduation Project\backend-distanceEstimator-main\vehicle-distance-estimationV2\distance-estimator\models\model@1535477330.h5')
print("Loaded model from disk")

def distance_estimation(labels,information,model,weights,results_dir):
    df = pd.DataFrame(information)
    print(len(df)==0)
    if(len(df)==0):
        return []
    print("data sent",df)
    y_test = df[['scaled_xmin', 'scaled_ymin', 'scaled_xmax', 'scaled_ymax']].values
    
    scalar = StandardScaler()
    y_test = scalar.fit_transform(y_test)
    scalar.fit_transform((df[['scaled_ymax']].values - df[['scaled_ymin']])/3)

    # evaluate loaded model on test data

    distance_pred = loaded_model.predict(y_test)
    # scale up predictions to original values
    distance_pred = scalar.inverse_transform(distance_pred)
    # save predictions
    df_result = df
    df_result['name']=labels
    df_result['distance'] = -100000
    for idx, row in df.iterrows():
        df_result.at[idx, 'distance'] = distance_pred[idx]
        print(df_result.at[idx, 'distance'])
        if(df_result.at[idx, 'distance']<4):
            print('7aseb')

    return df_result

@app.route("/",methods=["POST"])
def done():
    print('shghaal')
    return jsonify({'msg':'mya mya shghal'})

@app.route("/im_size", methods=["POST"])
def process_image():
    #print(request.form)
    filestr = request.form["image"]
    #print(filestr)
    filestr=filestr.replace('data:image/png;base64,','')
    #print(filestr)
    # while len(filestr) % 4 != 0:
    #     print(len(filestr))
    #     filestr += '='
    decoded_data = base64.b64decode(filestr)
    #print(decoded_data)
    img = Image.open(BytesIO(decoded_data)) 
 
    w,h=img.size

    # ret,img=cap.read()
    data=model(img)
    print(w,h)
    test=data.pandas().xyxy[0]
    print(test)
    labels=[]
    x1s=[]
    y1s=[]
    x2s=[]
    y2s=[]
    scaledX1s=[]
    scaledX2s=[]
    scaledY1s=[]
    scaledY2s=[]
    for i in range(test.shape[0]):
        x1 = int(test['xmin'][i])
        y1 = int(test['ymin'][i])
        x2 = int(test['xmax'][i])
        y2 = int(test['ymax'][i])
        labels.append(test['name'][i])
        print(x1,y1,x2,y2)
        x1s.append(x1)
        y1s.append(y1)
        x2s.append(x2)
        y2s.append(y2)
        originalvideoSize = (375,1242)
        originalvideoHieght = originalvideoSize[0]
        originalvideoWidth = originalvideoSize[1]
        imgHeight = originalvideoHieght
        imgWidth = originalvideoWidth
        scaledX1 = (x1/w)*originalvideoWidth
        scaledX1s.append(scaledX1)
        scaledX2 = (x2/w)*originalvideoWidth
        scaledX2s.append(scaledX2)
        scaledY1 =( y1/h)*originalvideoHieght
        scaledY1s.append(scaledY1)
        scaledY2 = (y2/h )*originalvideoHieght
        scaledY2s.append(scaledY2)
    data = {
    'xmin': x1s,
    'ymin': y1s,
    'xmax': x2s,
    'ymax': y2s,
    'scaled_xmin': scaledX1s,
    'scaled_ymin': scaledY1s,
    'scaled_xmax': scaledX2s,
    'scaled_ymax':scaledY2s
    }
    print(data)
    di=distance_estimation(labels,data,r'D:\fcis\Last Year\Graduation Project\backend-distanceEstimator-main\vehicle-distance-estimationV2\distance-estimator\models\model@1535477330.json',r'D:\fcis\Last Year\Graduation Project\backend-distanceEstimator-main\vehicle-distance-estimationV2\distance-estimator\models\model@1535477330.h5',r'D:\fcis\Last Year\Graduation Project\backend-distanceEstimator-main\vehicle-distance-estimationV2\distance-estimator')
    print('hello',di)
    #if len(di)!=0:
    # if di!=[]:
    #   di=di.to_json()
    #    return jsonify({'msg': 'success','test':di})
    #return jsonify({'msg': 'success','test':'nodata'})
    # return jsonify({'msg': 'success','test':di})
    array_of_objects=[]
    for index ,row in di.iterrows():
        array_of_objects.append(row.to_dict())
    return jsonify({'msg':'success','test':array_of_objects})
    # print('result---->')
    # print(test)
    # test=test.to_json()
    # return jsonify({'msg': 'success','test':test})

if __name__ == "__main__":
    app.run(debug=True)

    