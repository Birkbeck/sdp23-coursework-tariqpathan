f1: mov EAX 6
f2: mov EBX 1
f3: mov ECX 1
f4: mul EBX EAX
f5: mul EBX EBX
f6: jnz EAX f4
f7: out EBX
f8: sub EAX 1