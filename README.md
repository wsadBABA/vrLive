# vrLive
全景直播解决方案

maven springboot

HLS + three + nginx + install360 pro

思路：
install360 pro相机自定义rtmp服务器，推理至nginx，hls拉流播放，threejs解析，实现全景

优化一：
延迟较大是hls不可避免的问题，相比较之下rtmp是更好的选择，而rtmp前端播放大多基于flash，如果能有基于video或者canvas的播放方案，借助于three的canvas纹理构造函数，或许可以实现低延迟全景直播。

优化二：
根据部署环境（网络、硬件设备），调整nginx中hls服务参数，调试到最佳播放效果。

