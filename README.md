# EduTracker
EduTracker A Learning Journey Application that helps students record and track their academic progress throughout their studies.

## UML

```mermaid
classDiagram
    class Activity {
        <<abstract>>
        -int id
        -String name
        -Date startDate
        -Date endDate
        -ActivityType type 
    }
    
    class ActivityType{
        <<Enum>>
        STUDENT
        TEACHER
    }
    
    Activity --> ActivityType : contains
    
    
    class Module{
        -Mark mark
        -List~String~ skillTags
        -String degree        
    }
    Activity <|-- Module : inheritance
    
    class CalculateMark{
        +double calculateMark()
    }
    <<interface>> CalculateMark
    
    class Mark{
        -List~MarkItem~ markItems
        -double totalMark
    }
    
    class MarkItem{
        -String name
        -double mark
        -double proportion
    }
    
    CalculateMark <|-- Mark : implements
    CalculateMark <|-- MarkItem : implements
    
    Module "1" --> "1" Mark : Contains
    Mark "1" --> "0..*" MarkItem : Contains
    
    class ExtraCurriculum{
        -String content
        -String guidedTeacher
        -List ~String~ teammates
    }
    
    Activity <|-- ExtraCurriculum : inheritance
```

