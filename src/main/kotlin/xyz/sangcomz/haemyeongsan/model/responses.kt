package xyz.sangcomz.haemyeongsan.model

interface RpcData

data class PostThoughtResult(val thought: Thought) : RpcData