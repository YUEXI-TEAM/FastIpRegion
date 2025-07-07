# FastIpRegion 插件

## 概述
FastIpRegion 是一个用于获取玩家 IP 地理信息的插件，支持通过 PlaceholderAPI 提供变量功能，适用于全版本 Minecraft 服务器，并支持 Folia 核心。

## 依赖
- **PlaceholderAPI**

## 支持的核心
- Spigot
- Paper
- Folia

## 使用方法
通过 PlaceholderAPI 变量调用地理信息，变量格式为：
```
%FastIpRegion_{json字段}%
```

### 可用的 JSON 字段
以下是支持的 JSON 字段及其示例值：
```json
{
    "status": "success",
    "country": "香港",
    "countryCode": "HK",
    "region": "",
    "regionName": "Kowloon",
    "city": "香港",
    "zip": "999077",
    "lat": 22.3193,
    "lon": 114.169,
    "timezone": "Asia/Hong_Kong",
    "isp": "MoeChuang Network Limited",
    "org": "Private Customer",
    "as": "AS215672 MoeChuang Network Limited",
    "query": "151.242.10.11"
}
```
所有上述字段均可作为变量使用。例如：
- `%FastIpRegion_country%` 返回 `香港`
- `%FastIpRegion_city%` 返回 `香港`
- `%FastIpRegion_lat%` 返回 `22.3193`

## 开源
FastIpRegion 是一个开源插件，欢迎社区贡献代码或提出改进建议。