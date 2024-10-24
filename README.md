﻿# TechnicalTestCompose
Download the custom google-services.json and paste it in the app folder.

## Evidences
| Type | Sign Up | Sign In |
| ------------- | ------------- | ------------- |
| Success | https://github.com/user-attachments/assets/cdd71b6f-9e05-4822-9bff-87ceddbac8c2 | https://github.com/user-attachments/assets/f2d19953-559c-48b6-be06-267e1f9933a7 |
| Wrong  | https://github.com/user-attachments/assets/833a66e2-bdd7-43a2-bdf3-7bbe31ebab52 | https://github.com/user-attachments/assets/330d8383-b0b1-49f0-bfd8-393641c3b116 |

## Evidence Firebase
### Authentication
![image](https://github.com/user-attachments/assets/a73e9be7-634e-4a9b-b1da-148765ee388a)

### Storage
![image](https://github.com/user-attachments/assets/93187685-eeb7-40b4-9d6c-81dee81e1d66)

Folder of the user
![image](https://github.com/user-attachments/assets/e06a9cd1-ec1f-4fa7-9366-b2ea440d0c0b)

Photo of the user
![image](https://github.com/user-attachments/assets/96515854-2b87-4700-a2bd-1de196b948f4)

### FireStore Database
Architecture of the data
```
users
│
├── userId1
│   ├── params
│   └── cards
│       │
│       ├── cardId1
│       │   ├── params
│       │   │
│       │   └── movements
│       │       │
│       │       ├── movementId1
│       │       │   ├── params
│       │       │
│       │       ├── movementId2
│       │       │   ├── params
│       │
│       └── cardId2
│       │   ├── params
│       │       │
│       │       └── movements
│       │           ├── movementId1
│       │           │   ├── param
│       │           │
│       │           └── movementId2
│       │               ├── param
└──
```

Users collection
![image](https://github.com/user-attachments/assets/7641b795-7dea-4bb1-8e1f-59a777cdab93)

Cards collection
![image](https://github.com/user-attachments/assets/4887719b-f77d-4c42-b521-ec997b75147c)

Movements collection
![image](https://github.com/user-attachments/assets/13b96dc1-d7fb-4a98-9c4e-3894a35efb7d)




