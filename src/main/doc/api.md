# 影视分析平台


<a name="overview"></a>
## Overview
后端模块


### Version information
*Version* : 1.0


### URI scheme
*Host* : localhost:8080  
*BasePath* : /


### Tags

* 电影接口 : Movie Controller




<a name="paths"></a>
## Resources

<a name="f1c2d4f95697034a58357385c9341f70"></a>
### 电影接口
Movie Controller


<a name="getmovieusingget"></a>
#### 按id获取电影信息
```
GET /movie/{id}
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**id**  <br>*required*|id|string|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[电影实体](#b22074a99a461072432c9de30dd80bf5)|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Produces

* `*/*`


<a name="getmoviesusingget"></a>
#### 搜索结果
```
GET /search
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**page**  <br>*optional*|搜索页数|string|
|**Query**|**type**  <br>*optional*|类型|integer (int32)|
|**Query**|**words**  <br>*optional*|搜索关键词|string|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[电影列表返回参数](#ccdecb5611a33f764e60c47a73067d02)|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Produces

* `*/*`




<a name="definitions"></a>
## Definitions

<a name="ccdecb5611a33f764e60c47a73067d02"></a>
### 电影列表返回参数
电影列表返回参数


|Name|Description|Schema|
|---|---|---|
|**movies**  <br>*optional*|电影列表|< [电影实体](#b22074a99a461072432c9de30dd80bf5) > array|
|**msg**  <br>*optional*|返回信息|string|
|**size**  <br>*optional*|电影总数|integer (int32)|


<a name="b22074a99a461072432c9de30dd80bf5"></a>
### 电影实体

|Name|Schema|
|---|---|
|**actor**  <br>*optional*|string|
|**boxoffice**  <br>*optional*|number (float)|
|**duration**  <br>*optional*|integer (int32)|
|**firstBoxoffice**  <br>*optional*|number (float)|
|**id**  <br>*optional*|integer (int32)|
|**isIp**  <br>*optional*|boolean|
|**isNetwork**  <br>*optional*|boolean|
|**isSequel**  <br>*optional*|boolean|
|**issueCompany**  <br>*optional*|string|
|**manuCompany**  <br>*optional*|string|
|**releaseArea**  <br>*optional*|string|
|**releaseTime**  <br>*optional*|string (date-time)|
|**secondType**  <br>*optional*|string|
|**technology**  <br>*optional*|string|
|**title**  <br>*optional*|string|
|**type**  <br>*optional*|string|





